package me.iamee.linphone.e_linphone.model

import org.linphone.core.Content

data class ELinphoneContent(val utf8Text: String?) {
    companion object {
        fun from(content: Content?): ELinphoneContent? {
            if (content == null) return null
            return ELinphoneContent(utf8Text = content.utf8Text)
        }
    }
}