package com.tw

import com.tw.config.DatabaseFactory
import com.tw.plugins.configureRouting
import com.tw.plugins.configureSerialization
import com.tw.repository.ItemRepositoryImpl
import com.tw.service.ItemService
import io.ktor.server.application.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.cors.routing.*

fun main(args: Array<String>): Unit = EngineMain.main(args)

@Suppress("unused") // referenced in application.conf
fun Application.module() {
    DatabaseFactory.init()
    configureRouting(ItemService(ItemRepositoryImpl()))
    configureSerialization()
    install(CORS) { anyHost() }
}

@Suppress("unused") // referenced in application-test.conf
fun Application.moduleTest(itemService: ItemService) {
    configureRouting(itemService)
    configureSerialization()
}