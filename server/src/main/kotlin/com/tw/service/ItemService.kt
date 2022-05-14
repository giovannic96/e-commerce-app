package com.tw.service

import com.tw.entity.Item
import com.tw.exception.DuplicateItemException
import com.tw.repository.ItemRepository
import org.elasticsearch.ElasticsearchStatusException

class ItemService(private val itemRepository: ItemRepository) {

    fun insertOne(item: Item) {
        try {
            return itemRepository.insertOne(item)
        } catch (e: ElasticsearchStatusException) {
            throw DuplicateItemException("Cannot insert item ${item.id}: Item already exists.")
        }
    }

    fun getById(itemId: Long): Item? {
        return itemRepository.findById(itemId)
    }

    fun getAll(): List<Item> {
        return itemRepository.findAll()
    }
}
