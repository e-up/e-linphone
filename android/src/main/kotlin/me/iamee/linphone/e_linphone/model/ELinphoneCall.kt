package me.iamee.linphone.e_linphone.model

import org.linphone.core.Call

data class ELinphoneCall(
    val stats: Call.State?,
    val authenticationToken: String?,
    val averageQuality: Float,
    val cameraEnabled: Boolean
) {
    companion object {
        fun from(call: Call): ELinphoneCall {
            return ELinphoneCall(
                stats = call.state,
                authenticationToken = call.authenticationToken,
                averageQuality = call.averageQuality,
                cameraEnabled = call.cameraEnabled()
            )
        }
    }
}