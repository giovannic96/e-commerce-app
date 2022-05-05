package com.tw.plugins

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {

    routing {
        get("/api/v1/items") {
            println("I'm in!")
            call.respondText("{txt: 'Hello World!'}")
        }
    }
}