package me.iamee.linphone.e_linphone

import android.content.Context
import androidx.annotation.NonNull

import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.plugin.common.EventChannel
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import org.linphone.core.Core
import org.linphone.core.Factory
import org.linphone.core.TransportType

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

    private val sink: ELinphoneSink = ELinphoneSink()

    private lateinit var core: Core

    private val listener: ELinphoneCoreListener = ELinphoneCoreListener(sink = sink)

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
            transport = TRANSPORTS[transport?.toLowerCase()],
            userid = userid,
            ha1 = ha1,
            relam = realm,
            algorithm = algorithm
        )
        result.success(null)
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
        algorithm: String?
    ) {
        val authInfo = Factory.instance().createAuthInfo(username, userid, password, ha1, relam, domain, algorithm)
        val params = core.createAccountParams()
        val id = Factory.instance().createAddress("sip:$username@$domain")
        val address = Factory.instance().createAddress(uri)
        params.identityAddress = id
        transport?.run {
            address?.transport = this
        }
        params.serverAddress = address
        params.registerEnabled = true

        val account = core.createAccount(params)
        core.addAuthInfo(authInfo)
        core.addAccount(account)
        if (core.defaultAccount != null) {
            core.defaultAccount = account
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
