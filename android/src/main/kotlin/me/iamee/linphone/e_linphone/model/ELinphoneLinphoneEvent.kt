package me.iamee.linphone.e_linphone.model

import org.linphone.core.Event

data class ELinphoneLinphoneEvent(val name: String) {
    companion object {
        fun from(event: Event): ELinphoneLinphoneEvent {
            return ELinphoneLinphoneEvent(name = event.name)
        }
    }
}