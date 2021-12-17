package me.iamee.linphone.e_linphone.model

import org.linphone.core.Conference

data class ELinphoneConference(val id: String?) {

    companion object {
        fun from(conference: Conference): ELinphoneConference {
            return ELinphoneConference(id = conference.id)
        }
    }

}