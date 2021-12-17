package me.iamee.linphone.e_linphone.model

import org.linphone.core.ChatMessage

data class ELinphoneChatMessage(val isRead: Boolean) {
    companion object {
        fun from(chatMessage: ChatMessage): ELinphoneChatMessage {
            return ELinphoneChatMessage(isRead = chatMessage.isRead)
        }
    }
}