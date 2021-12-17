package me.iamee.linphone.e_linphone.model

import org.linphone.core.AudioDevice

data class ELinphoneAudioDevice(val deviceName: String) {
    companion object {
        fun from(audioDevice: AudioDevice): ELinphoneAudioDevice {
            return ELinphoneAudioDevice(deviceName = audioDevice.deviceName)
        }
    }
}