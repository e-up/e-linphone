package me.iamee.linphone.e_linphone

import me.iamee.linphone.e_linphone.model.*
import org.linphone.core.*

class ELinphoneCoreListener(private val sink: ELinphoneSink) : CoreListener {

    companion object {
//        private val TAG = "ELinphone-Listener"
    }

    override fun onLogCollectionUploadStateChanged(
        p0: Core,
        state: Core.LogCollectionUploadState?,
        p2: String
    ) {
        sink.success(LogCollectionUploadStateChanged(state = state, msg = p2))
    }

    override fun onEcCalibrationAudioUninit(p0: Core) {
        sink.success(EcCalibrationAudioUnInit())
    }

    override fun onCallIdUpdated(p0: Core, previousCallId: String, currentCallId: String) {
        sink.success(CallIdUpdated(previousCallId = previousCallId, currentCallId = currentCallId))
    }

    override fun onMessageReceived(p0: Core, chatRoom: ChatRoom, message: ChatMessage) {
        sink.success(
            MessageReceived(
                chatRoom = ELinphoneChatRoom.from(chatRoom),
                message = ELinphoneChatMessage.from(message)
            )
        )
    }

    override fun onEcCalibrationResult(p0: Core, status: EcCalibratorStatus?, delayMs: Int) {
        sink.success(EcCalibrationResult(status = status, delayMs = delayMs))
    }

    override fun onSubscriptionStateChanged(
        p0: Core,
        linphoneEvent: Event,
        state: SubscriptionState?
    ) {
        sink.success(
            SubscriptionStateChanged(
                event = ELinphoneLinphoneEvent.from(linphoneEvent),
                state = state
            )
        )
    }

    override fun onCallStatsUpdated(p0: Core, call: Call, callStats: CallStats) {
        sink.success(CallStatsUpdated(call = ELinphoneCall.from(call), callStats = callStats))
    }

    override fun onChatRoomStateChanged(p0: Core, chatRoom: ChatRoom, state: ChatRoom.State?) {
        sink.success(ChatRoomStateChanged(chatRoom = ELinphoneChatRoom.from(chatRoom), state = state))
    }

    override fun onBuddyInfoUpdated(p0: Core, friend: Friend) {
        sink.success(BuddyInfoUpdated(friend = ELinphoneFriend.from(friend)))
    }

    override fun onMessageSent(p0: Core, chatRoom: ChatRoom, message: ChatMessage) {
        sink.success(
            MessageSent(
                chatRoom = ELinphoneChatRoom.from(chatRoom),
                message = ELinphoneChatMessage.from(message)
            )
        )
    }

    override fun onInfoReceived(p0: Core, call: Call, message: InfoMessage) {
        sink.success(
            InfoReceived(
                call = ELinphoneCall.from(call),
                message = ELinphoneInfoMessage.from(message)
            )
        )
    }

    override fun onDtmfReceived(p0: Core, call: Call, dtmf: Int) {
        sink.success(DtmfReceived(call = ELinphoneCall.from(call), dtmf = dtmf))
    }

    override fun onTransferStateChanged(p0: Core, transfered: Call, callState: Call.State?) {
        sink.success(
            TransferStateChanged(
                transfered = ELinphoneCall.from(transfered),
                state = callState
            )
        )
    }

    override fun onLastCallEnded(p0: Core) {
        sink.success(LastCallEnded())
    }

    override fun onFriendListCreated(p0: Core, friendList: FriendList) {
        sink.success(FriendListCreated(friendList = ELinphoneFriendList.from(friendList)))
    }

    override fun onNotifyReceived(
        p0: Core,
        linphoneEvent: Event,
        notifiedEvent: String,
        body: Content
    ) {
        sink.success(
            NotifyReceived(
                event = ELinphoneLinphoneEvent.from(linphoneEvent),
                name = notifiedEvent,
                content = ELinphoneContent.from(body)
            )
        )
    }

    override fun onAuthenticationRequested(p0: Core, authInfo: AuthInfo, method: AuthMethod) {
        sink.success(
            AuthenticationRequested(
                authInfo = ELinphoneAuthInfo.from(authInfo),
                method = method
            )
        )
    }

    override fun onAccountRegistrationStateChanged(
        p0: Core,
        account: Account,
        state: RegistrationState?,
        msg: String
    ) {
        sink.success(
            AccountRegistrationStateChanged(
                account = ELinphoneAccount.from(account),
                state = state,
                msg = msg
            )
        )
    }

    override fun onAudioDevicesListUpdated(p0: Core) {
        sink.success(AudioDevicesListUpdated())
    }

    override fun onQrcodeFound(p0: Core, result: String?) {
        sink.success(QrcodeFound(result = result))
    }

    override fun onSubscribeReceived(
        p0: Core,
        linphoneEvent: Event,
        name: String,
        content: Content
    ) {
        sink.success(
            SubscribeReceived(
                event = linphoneEvent as ELinphoneEvent,
                name = name,
                content = ELinphoneContent.from(content)
            )
        )
    }

    override fun onEcCalibrationAudioInit(p0: Core) {
        sink.success(EcCalibrationAudioInit())
    }

    override fun onCallCreated(p0: Core, call: Call) {
        sink.success(CallCreated(call = ELinphoneCall.from(call)))
    }

    override fun onNewSubscriptionRequested(p0: Core, friend: Friend, url: String) {
        sink.success(NewSubscriptionRequested(friend = ELinphoneFriend.from(friend), url = url))
    }

    override fun onNotifyPresenceReceived(p0: Core, friend: Friend) {
        sink.success(NotifyPresenceReceived(friend = ELinphoneFriend.from(friend)))
    }

    override fun onPublishStateChanged(p0: Core, event: Event, state: PublishState?) {
        sink.success(PublishStateChanged(event = event as ELinphoneEvent, state = state))
    }

    override fun onLogCollectionUploadProgressIndication(p0: Core, offset: Int, total: Int) {
        sink.success(LogCollectionUploadProgressIndication(offset = offset, total = total))
    }

    override fun onIsComposingReceived(p0: Core, chatRoom: ChatRoom) {
        sink.success(IsComposingReceived(chatRoom = ELinphoneChatRoom.from(chatRoom)))
    }

    override fun onChatRoomEphemeralMessageDeleted(p0: Core, chatRoom: ChatRoom) {
        sink.success(ChatRoomEphemeralMessageDeleted(chatRoom = ELinphoneChatRoom.from(chatRoom)))
    }

    override fun onCallStateChanged(p0: Core, call: Call, state: Call.State?, message: String) {
        sink.success(
            CallStateChanged(
                call = ELinphoneCall.from(call),
                state = state,
                message = message
            )
        )
    }

    override fun onGlobalStateChanged(p0: Core, state: GlobalState?, message: String) {
        sink.success(GlobalStateChanged(state = state, message = message))
    }

    override fun onChatRoomRead(p0: Core, chatRoom: ChatRoom) {
        sink.success(ChatRoomRead(chatRoom = ELinphoneChatRoom.from(chatRoom)))
    }

    override fun onReferReceived(p0: Core, referTo: String) {
        sink.success(ReferReceived(referTo = referTo))
    }

    override fun onConfiguringStatus(p0: Core, state: ConfiguringState?, message: String?) {
        sink.success(ConfiguringStatus(status = state, message = message))
    }

    override fun onCallLogUpdated(p0: Core, callLog: CallLog) {
        sink.success(CallLogUpdated(callLog = ELinphoneCallLog.from(callLog)))
    }

    override fun onCallEncryptionChanged(
        p0: Core,
        call: Call,
        mediaEncryptionEnabled: Boolean,
        authenticationToken: String?
    ) {
        sink.success(
            CallEncryptionChanged(
                call = ELinphoneCall.from(call),
                mediaEncryptionEnabled = mediaEncryptionEnabled,
                authenticationToken = authenticationToken
            )
        )
    }

    override fun onImeeUserRegistration(p0: Core, status: Boolean, userId: String, info: String) {
        sink.success(ImeeUserRegistration(status = status, userId = userId, info = info))
    }

    override fun onRegistrationStateChanged(
        p0: Core,
        proxyConfig: ProxyConfig,
        state: RegistrationState?,
        message: String
    ) {
        sink.success(
            RegistrationStateChanged(
                proxyConfig = ELinphoneProxyConfig.from(proxyConfig),
                state = state,
                message = message
            )
        )
    }

    override fun onAudioDeviceChanged(p0: Core, audioDevice: AudioDevice) {
        sink.success(AudioDeviceChanged(device = ELinphoneAudioDevice.from(audioDevice)))
    }

    override fun onFirstCallStarted(p0: Core) {
        sink.success(FirstCallStarted())
    }

    override fun onMessageReceivedUnableDecrypt(
        p0: Core,
        chatRoom: ChatRoom,
        message: ChatMessage
    ) {
        sink.success(
            MessageReceivedUnableDecrypt(
                chatRoom = ELinphoneChatRoom.from(chatRoom),
                message = ELinphoneChatMessage.from(message)
            )
        )
    }

    override fun onVersionUpdateCheckResultReceived(
        p0: Core,
        result: VersionUpdateCheckResult,
        version: String?,
        url: String?
    ) {
        sink.success(
            VersionUpdateCheckResultReceived(
                result = result,
                version = version,
                url = url
            )
        )
    }

    override fun onChatRoomSubjectChanged(p0: Core, chatRoom: ChatRoom) {
        sink.success(ChatRoomSubjectChanged(chatRoom = ELinphoneChatRoom.from(chatRoom)))
    }

    override fun onNotifyPresenceReceivedForUriOrTel(
        p0: Core,
        friend: Friend,
        uriOrTel: String,
        presenceModel: PresenceModel
    ) {
        sink.success(
            NotifyPresenceReceivedForUriOrTel(
                friend = ELinphoneFriend.from(friend),
                uriOrTel = uriOrTel,
                model = ELinphonePresenceModel.from(presenceModel)
            )
        )
    }

    override fun onFriendListRemoved(p0: Core, friendList: FriendList) {
        sink.success(FriendListRemoved(friendList = ELinphoneFriendList.from(friendList)))
    }

    override fun onConferenceStateChanged(
        p0: Core,
        conference: Conference,
        state: Conference.State?
    ) {
        sink.success(
            ConferenceStateChanged(
                conference = ELinphoneConference.from(conference),
                state = state
            )
        )
    }

    override fun onNetworkReachable(p0: Core, reachable: Boolean) {
        sink.success(NetworkReachable(reachable = reachable))
    }
}