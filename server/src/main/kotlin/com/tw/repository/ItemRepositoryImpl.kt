package com.tw.repository

import com.jillesvangurp.eskotlinwrapper.dsl.match
import com.jillesvangurp.eskotlinwrapper.dsl.matchAll
import com.tw.config.DatabaseFactory.esClient
import com.tw.entity.Item
import org.elasticsearch.action.search.configure
import org.elasticsearch.client.indexRepository

class ItemRepositoryImpl: ItemRepository {

    override fun insertOne(item: Item) {
        val repo = esClient.indexRepository<Item>("item-index", refreshAllowed = true)
        repo.index(id=item.id.toString(), obj=item)
        repo.refresh() // ensure the document is committed
    }

    override fun findById(itemId: Long): Item? {
        val repo = esClient.indexRepository<Item>("item-index", refreshAllowed = true)

        val result = repo.search {
            configure {
                resultSize = 1
                query = match(Item::id, itemId.toString())
            }
        }

        return result.mappedHits.firstOrNull()
    }

    override fun findAll(): List<Item> {
        val repo = esClient.indexRepository<Item>("item-index", refreshAllowed = true)

        val result = repo.search {
            configure {
                query = matchAll()
            }
        }

        return result.mappedHits.toList()
    }
}