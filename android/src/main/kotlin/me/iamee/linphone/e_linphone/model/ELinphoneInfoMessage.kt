package me.iamee.linphone.e_linphone.model

import org.linphone.core.InfoMessage

data class ELinphoneInfoMessage(val content: ELinphoneContent?) {
    companion object {
        fun from(infoMessage: InfoMessage): ELinphoneInfoMessage {
            return ELinphoneInfoMessage(content = ELinphoneContent.from(infoMessage.content))
        }
    }
}