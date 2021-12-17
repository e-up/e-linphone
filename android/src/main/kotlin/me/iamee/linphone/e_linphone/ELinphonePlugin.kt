package me.iamee.linphone.e_linphone

import android.content.Context
import androidx.annotation.NonNull
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.ObjectMapper

import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.plugin.common.EventChannel
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import me.iamee.linphone.e_linphone.model.ELinphoneAccount
import org.linphone.core.*
import java.util.*
import kotlin.collections.HashMap

/** ELinphonePlugin */
class ELinphonePlugin : FlutterPlugin, MethodCallHandler, EventChannel.StreamHandler {
    /// The MethodChannel that will the communication between Flutter and native Android
    ///
    /// This local reference serves to register the plugin with the Flutter Engine and unregister it
    /// when the Flutter Engine is detached from the Activity

    companion object {
        private const val TAG = "ELinphone"
        private const val METHOD_CHANNEL_NAME = "e_linphone"
        private const val EVENT_CHANNEL_NAME = "e_linphone_event"
        private val MAPPER: ObjectMapper = ObjectMapper()

        init {
            MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL)
        }

        private val TRANSPORTS = mapOf<String, TransportType>(
            "udp" to TransportType.Udp,
            "tcp" to TransportType.Tcp,
            "tls" to TransportType.Tls,
            "dtls" to TransportType.Dtls
        )
    }

    private lateinit var methodChannel: MethodChannel

    private lateinit var eventChannel: EventChannel

    private lateinit var context: Context

    private lateinit var core: Core


    private val sink: ELinphoneSink = ELinphoneSink(mapper = MAPPER)

    private val listener: ELinphoneCoreListener = ELinphoneCoreListener(sink = sink)

    private val accounts: HashMap<String, Account> = HashMap()

    override fun onAttachedToEngine(@NonNull flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
        methodChannel = MethodChannel(flutterPluginBinding.binaryMessenger, METHOD_CHANNEL_NAME)
        methodChannel.setMethodCallHandler(this)
        eventChannel = EventChannel(flutterPluginBinding.binaryMessenger, EVENT_CHANNEL_NAME)
        eventChannel.setStreamHandler(this)
        context = flutterPluginBinding.applicationContext
        core = Factory.instance().createCore(null, null, context)
        core.addListener(listener)
    }

    override fun onMethodCall(@NonNull call: MethodCall, @NonNull result: Result) {
        if (call.method == "getPlatformVersion") {
            result.success("Android ${android.os.Build.VERSION.RELEASE}")
        } else if (call.method == "login") {
            login(call, result)
        }
    }

    private fun login(call: MethodCall, result: Result) {
        val uri: String? = call.argument("uri")
        val username: String? = call.argument("username")
        val password: String? = call.argument("password")
        val domain: String? = call.argument("domain")
        val transport: String? = call.argument("transport")
        val userid: String? = call.argument("userid")
        val ha1: String? = call.argument("ha1")
        val realm: String? = call.argument("realm")
        val algorithm: String? = call.argument("algorithm")
        login(
            uri = uri ?: "",
            username = username ?: "",
            password = password ?: "",
            domain = domain ?: "",
            transport = TRANSPORTS[transport?.toLowerCase(Locale.ROOT)],
            userid = userid,
            ha1 = ha1,
            relam = realm,
            algorithm = algorithm,
            result = result
        )
    }

    private fun login(
        uri: String,
        username: String,
        password: String,
        domain: String,
        transport: TransportType?,
        userid: String?,
        ha1: String?,
        relam: String?,
        algorithm: String?,
        result: Result
    ) {
        val authInfo = Factory.instance()
            .createAuthInfo(username, userid, password, ha1, relam, domain, algorithm)
        val params = core.createAccountParams()
        val id = "sip:$username@$domain"
        accounts[id]?.run {
            return result.success(MAPPER.writeValueAsString(ELinphoneAccount.from(this)))
        }
        val address = Factory.instance().createAddress(uri)
        params.identityAddress = Factory.instance().createAddress(id)
        transport?.run {
            address?.transport = this
        }
        params.serverAddress = address
        params.registerEnabled = true

        val coreAccount = core.createAccount(params)
        var accountListener: AccountListener? = null
        accountListener = AccountListener { account, state, msg ->
            if (state == RegistrationState.Ok) {
                result.success(MAPPER.writeValueAsString(ELinphoneAccount.from(account)))
                account.removeListener(accountListener)
            } else if (state == RegistrationState.Failed) {
                result.error("${ELinphoneException.LOGIN_FAILURE.code}", msg, null)
                accounts.remove(id)
                core.removeAccount(account)
                account.removeListener(accountListener)
            }
        }
        coreAccount.addListener(accountListener)
        core.addAuthInfo(authInfo)
        core.addAccount(coreAccount)
        accounts[id] = coreAccount
        if (core.defaultAccount != null) {
            core.defaultAccount = coreAccount
        }
        core.start()
    }

    override fun onDetachedFromEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {
        core.removeListener(listener)
        core.defaultAccount?.run { core.removeAccount(this) }
        core.clearAccounts()
        core.clearAllAuthInfo()
        core.stop()
        methodChannel.setMethodCallHandler(null)
    }

    override fun onListen(arguments: Any?, events: EventChannel.EventSink?) {
        sink.delegate = events
    }

    override fun onCancel(arguments: Any?) {
        sink.delegate = null
    }
}
