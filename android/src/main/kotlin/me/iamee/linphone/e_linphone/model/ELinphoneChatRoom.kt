package me.iamee.linphone.e_linphone.model

import org.linphone.core.ChatRoom

data class ELinphoneChatRoom(
    val char: Int
) {
    companion object {
        fun from(chatRoom: ChatRoom): ELinphoneChatRoom {
            return ELinphoneChatRoom(char = chatRoom.char)
        }
    }
}