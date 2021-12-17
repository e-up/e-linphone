package me.iamee.linphone.e_linphone.model

import org.linphone.core.PresenceModel

data class ELinphonePresenceModel(val isOnline: Boolean) {
    companion object {
        fun from(presenceModel: PresenceModel): ELinphonePresenceModel {
            return ELinphonePresenceModel(isOnline = presenceModel.isOnline)
        }
    }
}