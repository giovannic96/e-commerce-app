package com.tw.utility

import com.tw.moduleTest
import com.tw.service.ItemService
import io.ktor.client.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.config.*
import io.ktor.server.testing.*

fun ApplicationTestBuilder.configureClient(): HttpClient {
    return createClient {
        install(ContentNegotiation) {
            json()
        }
    }
}

fun ApplicationTestBuilder.configure(itemService: ItemService) {
    environment {
        config = ApplicationConfig("application-test.conf")
        module {
            moduleTest(itemService)
        }
    }
}