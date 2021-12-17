package me.iamee.linphone.e_linphone.model

import org.linphone.core.Address

data class ELinphoneAddress(
    val displayName: String?,
    val username: String?,
    val uri: String?,
    val domain: String?,
    val port: Int,
    val scheme: String?,
    val secure: Boolean,
    val isSip: Boolean
) {
    companion object {
        fun fromAddress(address: Address?): ELinphoneAddress? {
            if (address == null) return null
            return ELinphoneAddress(
                displayName = address.displayName,
                username = address.username,
                domain = address.domain,
                port = address.port,
                scheme = address.scheme,
                uri = address.asStringUriOnly(),
                isSip = address.isSip,
                secure = address.secure
            )
        }
    }
}