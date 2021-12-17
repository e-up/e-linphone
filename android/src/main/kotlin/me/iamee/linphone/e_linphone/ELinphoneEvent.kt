package me.iamee.linphone.e_linphone

import com.fasterxml.jackson.annotation.JsonTypeInfo
import me.iamee.linphone.e_linphone.model.*
import org.linphone.core.*

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "action")
abstract class ELinphoneEvent {}

data class AccountRegistrationStateChanged(
    val account: ELinphoneAccount,
    val state: RegistrationState?,
    val msg: String?
) : ELinphoneEvent()

data class LogCollectionUploadStateChanged(
    val state: Core.LogCollectionUploadState?,
    val msg: String?
) : ELinphoneEvent()

class EcCalibrationAudioUnInit() : ELinphoneEvent()

data class CallIdUpdated(val previousCallId: String, val currentCallId: String) : ELinphoneEvent()

data class MessageReceived(val chatRoom: ELinphoneChatRoom, val message: ELinphoneChatMessage) : ELinphoneEvent()

data class EcCalibrationResult(val status: EcCalibratorStatus?, val delayMs: Int) : ELinphoneEvent()

data class SubscriptionStateChanged(val event: ELinphoneLinphoneEvent, val state: SubscriptionState?) : ELinphoneEvent()

data class CallStatsUpdated(val call: ELinphoneCall, val callStats: CallStats) : ELinphoneEvent()

data class ChatRoomStateChanged(val chatRoom: ELinphoneChatRoom, val state: ChatRoom.State?) : ELinphoneEvent()

data class BuddyInfoUpdated(val friend: ELinphoneFriend) : ELinphoneEvent()

data class MessageSent(val chatRoom: ELinphoneChatRoom, val message: ELinphoneChatMessage) : ELinphoneEvent()

data class InfoReceived(val call: ELinphoneCall, val message: ELinphoneInfoMessage) : ELinphoneEvent()

data class DtmfReceived(val call: ELinphoneCall, val dtmf: Int) : ELinphoneEvent()

data class TransferStateChanged(val transfered: ELinphoneCall, val state: Call.State?) : ELinphoneEvent()

class LastCallEnded() : ELinphoneEvent()

data class FriendListCreated(val friendList: ELinphoneFriendList) : ELinphoneEvent()

data class NotifyReceived(val event: ELinphoneLinphoneEvent, val name: String, val content: ELinphoneContent?) :
    ELinphoneEvent()

data class AuthenticationRequested(val authInfo: ELinphoneAuthInfo, val method: AuthMethod) : ELinphoneEvent()

class AudioDevicesListUpdated() : ELinphoneEvent()

data class QrcodeFound(val result: String?) : ELinphoneEvent()

data class SubscribeReceived(val event: ELinphoneEvent, val name: String, val content: ELinphoneContent?) :
    ELinphoneEvent()

class EcCalibrationAudioInit() : ELinphoneEvent()

data class CallCreated(val call: ELinphoneCall) : ELinphoneEvent()

data class NewSubscriptionRequested(val friend: ELinphoneFriend, val url: String) : ELinphoneEvent()

data class NotifyPresenceReceived(val friend: ELinphoneFriend) : ELinphoneEvent()

data class PublishStateChanged(val event: ELinphoneEvent, val state: PublishState?) : ELinphoneEvent()

data class LogCollectionUploadProgressIndication(val offset: Int, val total: Int) : ELinphoneEvent()

data class IsComposingReceived(val chatRoom: ELinphoneChatRoom) : ELinphoneEvent()

data class ChatRoomEphemeralMessageDeleted(val chatRoom: ELinphoneChatRoom) : ELinphoneEvent()

data class CallStateChanged(val call: ELinphoneCall, val state: Call.State?, val message: String) : ELinphoneEvent()

data class GlobalStateChanged(val state: GlobalState?, val message: String) : ELinphoneEvent()

data class ChatRoomRead(val chatRoom: ELinphoneChatRoom) : ELinphoneEvent()

data class ReferReceived(val referTo: String) : ELinphoneEvent()

data class ConfiguringStatus(val status: ConfiguringState?, val message: String?) : ELinphoneEvent()

data class CallLogUpdated(val callLog: ELinphoneCallLog) : ELinphoneEvent()

data class CallEncryptionChanged(
    val call: ELinphoneCall,
    val mediaEncryptionEnabled: Boolean,
    val authenticationToken: String?
)

data class ImeeUserRegistration(val status: Boolean, val userId: String, val info: String) : ELinphoneEvent()

data class RegistrationStateChanged(
    val proxyConfig: ELinphoneProxyConfig,
    val state: RegistrationState?,
    val message: String
) : ELinphoneEvent()

data class AudioDeviceChanged(val device: ELinphoneAudioDevice) : ELinphoneEvent()

class FirstCallStarted : ELinphoneEvent()

data class MessageReceivedUnableDecrypt(val chatRoom: ELinphoneChatRoom, val message: ELinphoneChatMessage) :
    ELinphoneEvent()

data class VersionUpdateCheckResultReceived(
    val result: VersionUpdateCheckResult,
    val version: String?,
    val url: String?
) : ELinphoneEvent()

data class ChatRoomSubjectChanged(val chatRoom: ELinphoneChatRoom) : ELinphoneEvent()

data class NotifyPresenceReceivedForUriOrTel(
    val friend: ELinphoneFriend,
    val uriOrTel: String,
    val model: ELinphonePresenceModel
) : ELinphoneEvent()

data class FriendListRemoved(val friendList: ELinphoneFriendList) : ELinphoneEvent()

data class ConferenceStateChanged(val conference: ELinphoneConference, val state: Conference.State?) : ELinphoneEvent()

data class NetworkReachable(val reachable: Boolean) : ELinphoneEvent()