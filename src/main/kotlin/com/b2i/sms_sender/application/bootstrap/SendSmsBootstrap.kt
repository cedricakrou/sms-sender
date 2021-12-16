package com.b2i.sms_sender.application.bootstrap

import com.b2i.sms_sender.domain.contact.entity.Contact
import com.b2i.sms_sender.infrastructure.services.sms.ISmsService
import com.b2i.sms_sender.infrastructure.services.sms.aroli.SmsText

object SendSmsBootstrap {

    fun seed( iSmsService: ISmsService ) {

        val listContacts : MutableList<Contact> = mutableListOf(


            Contact(
                firstName = "",
                lastName = "",
                phoneNumber = "0708483575"
            ),
            Contact(
                firstName = "",
                lastName = "",
                phoneNumber = "0707461281"
            ),
            Contact(
                firstName = "",
                lastName = "",
                phoneNumber = "0749300265"
            ),
            Contact(
                firstName = "",
                lastName = "",
                phoneNumber = "0767626349"
            )

        )


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