package com.tw.plugins

import com.tw.routes.itemRouting
import com.tw.service.ItemService
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureRouting(itemService: ItemService) {
    routing {
        itemRouting(itemService)
    }
}