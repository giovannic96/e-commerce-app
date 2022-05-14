package com.tw.doubles

import com.tw.entity.Item
import com.tw.exception.DuplicateItemException
import com.tw.repository.ItemRepository

class FakeItemRepository: ItemRepository {
    private val items = mutableListOf<Item>()

    override fun insertOne(item: Item) {
        if(items.find { it.id == item.id} != null)
            throw DuplicateItemException("Cannot insert item ${item.id}: Item already exists.")
        items.add(item)
    }

    override fun findById(itemId: Long): Item? {
        return items.find { it.id == itemId }
    }

    override fun findAll(): List<Item> {
        return items
    }
}