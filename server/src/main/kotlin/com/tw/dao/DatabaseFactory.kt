package com.tw.dao

import com.tw.exception.DbInitException
import org.elasticsearch.client.RestHighLevelClient
import org.elasticsearch.client.create


object DatabaseFactory {
    lateinit var esClient: RestHighLevelClient

    fun init() {
        val esHost = System.getenv("ECOMMERCE_ES_HOST")?.toString()
        val esPort = System.getenv("ECOMMERCE_ES_PORT")?.toInt()

        if(esHost != null && esPort != null) {
            esClient = create(
                host = esHost,
                port = esPort,
            )
        } else {
            throw DbInitException("Cannot create es-client, please specify a correct host and port.")
        }
    }
}