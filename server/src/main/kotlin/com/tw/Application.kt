package com.tw

import com.tw.dao.DatabaseFactory
import com.tw.plugins.configureRouting
import com.tw.plugins.configureSerialization
import io.ktor.server.application.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.cors.routing.*

fun main(args: Array<String>): Unit = EngineMain.main(args)

fun Application.module() {
    install(CORS) { anyHost() }
    DatabaseFactory.init()
    configureRouting()
    configureSerialization()
}