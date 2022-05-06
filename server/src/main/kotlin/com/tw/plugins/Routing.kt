package com.tw.plugins

import com.jillesvangurp.eskotlinwrapper.dsl.matchAll
import com.tw.dao.DatabaseFactory.esClient
import com.tw.models.Item
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.elasticsearch.action.search.configure
import org.elasticsearch.client.indexRepository

fun Application.configureRouting() {

    routing {
        get("/api/v1/items") {
            val itemRepository = esClient.indexRepository<Item>("item-index", refreshAllowed = true)
            itemRepository.index(obj=Item("Hello World!"))
            itemRepository.refresh() // ensure the document is committed
            val results = itemRepository.search {
                configure {
                    query = matchAll()
                }
            }
            println(results.mappedHits.first().description)
            call.respondText("{${results.mappedHits.first().description}")
        }
    }
}