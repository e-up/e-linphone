package me.iamee.linphone.e_linphone.model

import org.linphone.core.Friend

data class ELinphoneFriend(val name: String?) {

    companion object {
        fun from(friend: Friend): ELinphoneFriend {
            return ELinphoneFriend(name = friend.name)
        }
    }

}