package com.tw.routes

import com.tw.entity.Item
import com.tw.exception.DuplicateItemException
import com.tw.service.ItemService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.util.*

fun Route.itemRouting(itemService: ItemService) {

    route("/api/v1/items") {
        get {
            val items = itemService.getAll()
            call.respond(HttpStatusCode.OK, items)
        }

        get("{id}") {
            val itemId = call.parameters.getOrFail("id").toLong()
            val item = itemService.getById(itemId)

            if(item == null)
                call.respond(HttpStatusCode.NotFound)
            else
                call.respond(HttpStatusCode.OK, item)
        }

        post {
            val item = call.receive<Item>()
            try {
                itemService.insertOne(item)
                call.respond(HttpStatusCode.Created)
            } catch (e: DuplicateItemException) {
                call.respond(HttpStatusCode.Conflict, e.message ?: "")
            }
        }
    }
}