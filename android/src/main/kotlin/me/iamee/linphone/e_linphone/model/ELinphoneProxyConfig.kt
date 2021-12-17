package me.iamee.linphone.e_linphone.model

import org.linphone.core.ProxyConfig

data class ELinphoneProxyConfig(val domain: String?) {
    companion object {
        fun from(proxyConfig: ProxyConfig): ELinphoneProxyConfig {
            return ELinphoneProxyConfig(domain = proxyConfig.domain)
        }
    }
}