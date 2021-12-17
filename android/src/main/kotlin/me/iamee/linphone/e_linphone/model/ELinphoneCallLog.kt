package me.iamee.linphone.e_linphone.model

import org.linphone.core.CallLog

data class ELinphoneCallLog(val duration: Int) {
    companion object {
        fun from(callLog: CallLog): ELinphoneCallLog {
            return ELinphoneCallLog(duration = callLog.duration)
        }
    }
}