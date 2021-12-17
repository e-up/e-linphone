package me.iamee.linphone.e_linphone

import com.fasterxml.jackson.databind.ObjectMapper
import io.flutter.plugin.common.EventChannel
import io.flutter.plugin.common.EventChannel.EventSink

class ELinphoneSink(val mapper: ObjectMapper) : EventSink {

    var delegate: EventChannel.EventSink? = null
        set(value) {
            field = value;
        }

    override fun success(event: Any?) {
        delegate?.success(mapper.writeValueAsString(event))
    }

    override fun error(errorCode: String?, errorMessage: String?, errorDetails: Any?) {
        delegate?.error(errorCode, errorMessage, errorDetails)
    }


    override fun endOfStream() {
        delegate?.endOfStream()
    }
}