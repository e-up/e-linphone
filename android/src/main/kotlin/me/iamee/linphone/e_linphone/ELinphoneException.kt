package me.iamee.linphone.e_linphone

enum class ELinphoneException(val code: Int) {
    LOGIN_FAILURE(code = 1),
    CALL_FAILURE(code = 2)
}