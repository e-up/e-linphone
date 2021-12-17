package me.iamee.linphone.e_linphone.model

import org.linphone.core.FriendList

data class ELinphoneFriendList(val displayName: String?) {
    companion object {
        fun from(friendList: FriendList): ELinphoneFriendList {
            return ELinphoneFriendList(displayName = friendList.displayName)
        }
    }
}