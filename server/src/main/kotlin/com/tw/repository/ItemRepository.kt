package com.tw.repository

import com.tw.entity.Item

interface ItemRepository {

    fun insertOne(item: Item)
    fun findById(itemId: Long): Item?
    fun findAll(): List<Item>
}