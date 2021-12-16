package me.iamee.linphone.e_linphone

import io.flutter.plugin.common.EventChannel
import io.flutter.plugin.common.EventChannel.EventSink

class ELinphoneSink : EventSink {

    var delegate: EventChannel.EventSink? = null
        set(value) {
            field = value;
        }

    override fun success(event: Any?) {
        delegate?.success(event)
    }

    override fun error(errorCode: String?, errorMessage: String?, errorDetails: Any?) {
        delegate?.error(errorCode, errorMessage, errorDetails)
    }


    override fun endOfStream() {
        delegate?.endOfStream()
    }
}