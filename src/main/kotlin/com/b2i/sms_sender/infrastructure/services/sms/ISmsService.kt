package com.b2i.sms_sender.infrastructure.services.sms

import com.b2i.sms_sender.infrastructure.services.sms.aroli.Dlr
import com.b2i.sms_sender.infrastructure.services.sms.aroli.DlrMethod
import com.b2i.sms_sender.infrastructure.services.sms.aroli.ListCoding
import com.b2i.sms_sender.infrastructure.services.sms.aroli.SmsResponseFormat

interface ISmsService {

    fun sendSms(
        from: String = "Botti Billy",
        to: String,
        content: String,
        coding: Int = ListCoding.DEFAULT,
        dlr: String = Dlr.no,
        dlrmethod: String = DlrMethod.POST,
        countryCode : String = "225"
    ) : SmsResponseFormat

}