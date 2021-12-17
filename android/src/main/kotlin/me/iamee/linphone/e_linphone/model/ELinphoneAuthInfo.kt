package me.iamee.linphone.e_linphone.model

import org.linphone.core.AuthInfo

data class ELinphoneAuthInfo(val algorithm: String?) {
    companion object {
        fun from(authInfo: AuthInfo): ELinphoneAuthInfo {
            return ELinphoneAuthInfo(algorithm = authInfo.algorithm)
        }
    }
}