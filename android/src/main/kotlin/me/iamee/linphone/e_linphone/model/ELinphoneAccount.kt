package me.iamee.linphone.e_linphone.model

import org.linphone.core.Account
import org.linphone.core.Reason

data class ELinphoneAccount constructor(
    val address: ELinphoneAddress?,
    val error: Reason
) {
    companion object {
        fun from(account: Account): ELinphoneAccount {
            return ELinphoneAccount(
                address = ELinphoneAddress.fromAddress(address = account.contactAddress),
                error = account.error
            )
        }
    }
}