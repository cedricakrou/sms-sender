package com.b2i.sms_sender.infrastructure.services.sms.aroli

import com.b2i.sms_sender.domain.contact.entity.Contact


object SmsText{

    fun textContact(model: Contact) = """  
NOM: ${model.firstName}
PRENOM: ${model.lastName} 
NUMERO : ${model.phoneNumber} 
         """.trimIndent()


    fun textOpticalDiscount(model: Contact) = """  
Du 10 décembre 2021 au ,08 janvier 2022 , profiter de 35% de réduction,sur toutes les montures chez OPTICAL Discount Abidjan.
2721244260/2721255966
         """.trimIndent()

}