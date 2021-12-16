package com.b2i.sms_sender.infrastructure.services.sms

import com.b2i.sms_sender.infrastructure.services.sms.aroli.DlrMethod
import com.b2i.sms_sender.infrastructure.services.sms.aroli.SmsFormat
import com.b2i.sms_sender.infrastructure.services.sms.aroli.SmsResponseFormat
import com.b2i.sms_sender.infrastructure.services.sms.aroli.StatusSmsResponse
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.http.*
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate


@Service
class SmsService(private val restTemplate: RestTemplate) : ISmsService {


    companion object{
        val BASE_URL = "https://campaigns.nerhysms.com/api/send"
        val APP_ID = "60f80f95cd7ca"
        val TOKEN = "45a6475496b03261b9a1df5a75"
        val BEARER = "Bearer ${com.b2i.sms_sender.infrastructure.services.sms.SmsService.Companion.TOKEN}"
    }

    override fun sendSms(
            from: String,
            to: String,
            content: String,
            coding: Int,
            dlr: String,
            dlrmethod: String,
            countryCode : String
    ) : SmsResponseFormat {


        val smsFormat = SmsFormat(
                from = from,
                to = countryCode + to,
                content = content,
        )

        val objectMapper = ObjectMapper().writeValueAsString(smsFormat)

        val headers = createHttpHeaders(BEARER)

        val method =  if( dlrmethod == DlrMethod.POST ) HttpMethod.POST else ( HttpMethod.GET )

        val entity: HttpEntity<String> = HttpEntity(objectMapper, headers)

        var smsReponse : SmsResponseFormat =  SmsResponseFormat()

         try {

            val resp : ResponseEntity<String> = restTemplate.exchange(BASE_URL, method, entity, String::class.java)

            if ( resp.statusCodeValue == 200 ) {

                smsReponse = ObjectMapper().readValue( resp.body, SmsResponseFormat::class.java )

                smsReponse.status = StatusSmsResponse.Success
            }
            else smsReponse.status = StatusSmsResponse.ErrorEmptyData

        } catch ( ex : Exception) {
            smsReponse.status = StatusSmsResponse.ErrorServer( exception = ex )
        }

        return smsReponse
    }


    private fun createHttpHeaders(bearer: String): HttpHeaders {
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON
        headers.add("Authorization", bearer)
        return headers
    }

}