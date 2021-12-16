package com.b2i.sms_sender

import com.b2i.sms_sender.application.bootstrap.SendSmsBootstrap
import com.b2i.sms_sender.infrastructure.services.sms.ISmsService
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.web.client.RestTemplate

@SpringBootApplication
class SmsSenderApplication {

    @Bean
    fun init(
        iSmsService: ISmsService
    ) : CommandLineRunner {

        return CommandLineRunner {

            SendSmsBootstrap.seed( iSmsService )

        }

    }


    @Bean
    fun restTemplate(builder: RestTemplateBuilder): RestTemplate = builder.build()

}

fun main(args: Array<String>) {
    runApplication<SmsSenderApplication>(*args)
}
