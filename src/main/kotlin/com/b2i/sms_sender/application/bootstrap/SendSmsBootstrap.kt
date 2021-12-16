package com.b2i.sms_sender.application.bootstrap

import com.b2i.sms_sender.domain.contact.entity.Contact
import com.b2i.sms_sender.infrastructure.services.sms.ISmsService
import com.b2i.sms_sender.infrastructure.services.sms.aroli.SmsText

object SendSmsBootstrap {

    fun seed( iSmsService: ISmsService ) {

        var listContacts : MutableList<Contact> = mutableListOf()

        listContacts =  ExcelReader.reader()

        listContacts.forEach {

            Thread {

                iSmsService.sendSms(
                    to = it.phoneNumber,
                    content = SmsText.textOpticalDiscount( it ),
                    countryCode = it.indicative
                )

            }.start()


        }

    }

}