package me.iamee.linphone.e_linphone

import io.flutter.Log
import org.linphone.core.*

class ELinphoneCoreListener(private val sink: ELinphoneSink) : CoreListener {

    companion object {
        private val TAG = "ELinphone-Listener"
    }

    override fun onLogCollectionUploadStateChanged(p0: Core, p1: Core.LogCollectionUploadState?, p2: String) {
        Log.d(TAG, "onLogCollectionUploadStateChanged")
    }

    override fun onEcCalibrationAudioUninit(p0: Core) {
        Log.d(TAG, "onEcCalibrationAudioUninit")
    }

    override fun onCallIdUpdated(p0: Core, p1: String, p2: String) {
        Log.d(TAG, "onCallIdUpdated")
    }

    override fun onMessageReceived(p0: Core, p1: ChatRoom, p2: ChatMessage) {
        Log.d(TAG, "onMessageReceived")
    }

    override fun onEcCalibrationResult(p0: Core, p1: EcCalibratorStatus?, p2: Int) {
        Log.d(TAG, "onEcCalibrationResult")
    }

    override fun onSubscriptionStateChanged(p0: Core, p1: Event, p2: SubscriptionState?) {
        Log.d(TAG, "onSubscriptionStateChanged")
    }

    override fun onCallStatsUpdated(p0: Core, p1: Call, p2: CallStats) {
        Log.d(TAG, "onCallStatsUpdated")
    }

    override fun onChatRoomStateChanged(p0: Core, p1: ChatRoom, p2: ChatRoom.State?) {
        Log.d(TAG, "onChatRoomStateChanged")
    }

    override fun onBuddyInfoUpdated(p0: Core, p1: Friend) {
        Log.d(TAG, "onBuddyInfoUpdated")
    }

    override fun onMessageSent(p0: Core, p1: ChatRoom, p2: ChatMessage) {
        Log.d(TAG, "onMessageSent")
    }

    override fun onInfoReceived(p0: Core, p1: Call, p2: InfoMessage) {
        Log.d(TAG, "onInfoReceived")
    }

    override fun onDtmfReceived(p0: Core, p1: Call, p2: Int) {
        Log.d(TAG, "onDtmfReceived")
    }

    override fun onTransferStateChanged(p0: Core, p1: Call, p2: Call.State?) {
        Log.d(TAG, "onTransferStateChanged")
    }

    override fun onLastCallEnded(p0: Core) {
        Log.d(TAG, "onLastCallEnded")
    }

    override fun onFriendListCreated(p0: Core, p1: FriendList) {
        Log.d(TAG, "onFriendListCreated")
    }

    override fun onNotifyReceived(p0: Core, p1: Event, p2: String, p3: Content) {
        Log.d(TAG, "onNotifyReceived")
    }

    override fun onAuthenticationRequested(p0: Core, p1: AuthInfo, p2: AuthMethod) {
        Log.d(TAG, "onAuthenticationRequested")
    }

    override fun onAccountRegistrationStateChanged(p0: Core, p1: Account, p2: RegistrationState?, p3: String) {
        Log.d(TAG, "onAccountRegistrationStateChanged")
    }

    override fun onAudioDevicesListUpdated(p0: Core) {
        Log.d(TAG, "onAudioDevicesListUpdated")
    }

    override fun onQrcodeFound(p0: Core, p1: String?) {
        Log.d(TAG, "onQrcodeFound")
    }

    override fun onSubscribeReceived(p0: Core, p1: Event, p2: String, p3: Content) {
        Log.d(TAG, "onSubscribeReceived")
    }

    override fun onEcCalibrationAudioInit(p0: Core) {
        Log.d(TAG, "onEcCalibrationAudioInit")
    }

    override fun onCallCreated(p0: Core, p1: Call) {
        Log.d(TAG, "onCallCreated")
    }

    override fun onNewSubscriptionRequested(p0: Core, p1: Friend, p2: String) {
        Log.d(TAG, "onNewSubscriptionRequested")
    }

    override fun onNotifyPresenceReceived(p0: Core, p1: Friend) {
        Log.d(TAG, "onNotifyPresenceReceived")
    }

    override fun onPublishStateChanged(p0: Core, p1: Event, p2: PublishState?) {
        Log.d(TAG, "onPublishStateChanged")
    }

    override fun onLogCollectionUploadProgressIndication(p0: Core, p1: Int, p2: Int) {
        Log.d(TAG, "onLogCollectionUploadProgressIndication")
    }

    override fun onIsComposingReceived(p0: Core, p1: ChatRoom) {
        Log.d(TAG, "onIsComposingReceived")
    }

    override fun onChatRoomEphemeralMessageDeleted(p0: Core, p1: ChatRoom) {
        Log.d(TAG, "onChatRoomEphemeralMessageDeleted")
    }

    override fun onCallStateChanged(p0: Core, p1: Call, p2: Call.State?, p3: String) {
        Log.d(TAG, "onCallStateChanged")
    }

    override fun onGlobalStateChanged(p0: Core, p1: GlobalState?, p2: String) {
        Log.d(TAG, "onGlobalStateChanged")
    }

    override fun onChatRoomRead(p0: Core, p1: ChatRoom) {
        Log.d(TAG, "onChatRoomRead")
    }

    override fun onReferReceived(p0: Core, p1: String) {
        Log.d(TAG, "onReferReceived")
    }

    override fun onConfiguringStatus(p0: Core, p1: ConfiguringState?, p2: String?) {
        Log.d(TAG, "onConfiguringStatus")
    }

    override fun onCallLogUpdated(p0: Core, p1: CallLog) {
        Log.d(TAG, "onCallLogUpdated")
    }

    override fun onCallEncryptionChanged(p0: Core, p1: Call, p2: Boolean, p3: String?) {
        Log.d(TAG, "onCallEncryptionChanged")
    }

    override fun onImeeUserRegistration(p0: Core, p1: Boolean, p2: String, p3: String) {
        Log.d(TAG, "onImeeUserRegistration")
    }

    override fun onRegistrationStateChanged(p0: Core, p1: ProxyConfig, p2: RegistrationState?, p3: String) {
        Log.d(TAG, "onRegistrationStateChanged")
        sink.success(p3);
    }

    override fun onAudioDeviceChanged(p0: Core, p1: AudioDevice) {
        Log.d(TAG, "onAudioDeviceChanged")
    }

    override fun onFirstCallStarted(p0: Core) {
        Log.d(TAG, "onFirstCallStarted")
    }

    override fun onMessageReceivedUnableDecrypt(p0: Core, p1: ChatRoom, p2: ChatMessage) {
        Log.d(TAG, "onMessageReceivedUnableDecrypt")
    }

    override fun onVersionUpdateCheckResultReceived(p0: Core, p1: VersionUpdateCheckResult, p2: String?, p3: String?) {
        Log.d(TAG, "onVersionUpdateCheckResultReceived")
    }

    override fun onChatRoomSubjectChanged(p0: Core, p1: ChatRoom) {
        Log.d(TAG, "onChatRoomSubjectChanged")
    }

    override fun onNotifyPresenceReceivedForUriOrTel(p0: Core, p1: Friend, p2: String, p3: PresenceModel) {
        Log.d(TAG, "onNotifyPresenceReceivedForUriOrTel")
    }

    override fun onFriendListRemoved(p0: Core, p1: FriendList) {
        Log.d(TAG, "onFriendListRemoved")
    }

    override fun onConferenceStateChanged(p0: Core, p1: Conference, p2: Conference.State?) {
        Log.d(TAG, "onConferenceStateChanged")
    }

    override fun onNetworkReachable(p0: Core, p1: Boolean) {
        Log.d(TAG, "onNetworkReachable")
    }
}