package com.tw.service

import com.tw.doubles.FakeItemRepository
import com.tw.entity.Item
import com.tw.exception.DuplicateItemException
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.random.Random
import kotlin.test.assertNotNull
import kotlin.test.assertNull

private const val ITEM_DESCRIPTION = "Harry Potter"

internal class ItemServiceTest {

    private lateinit var itemRepository: FakeItemRepository
    private lateinit var itemService: ItemService

    @BeforeEach
    internal fun setUp() {
        itemRepository = FakeItemRepository()
        itemService = ItemService(itemRepository)
    }

    @Test
    internal fun shouldInsertItemWhenValidItemIsProvided() {
        val item = Item(Random.nextLong(), ITEM_DESCRIPTION)
        itemService.insertOne(item)
        assertNotNull(itemService.getById(item.id))
    }

    @Test
    internal fun shouldReturnNullWhenGetNotPresentItem() {
        val item = Item(1L, ITEM_DESCRIPTION)
        itemService.insertOne(item)
        assertNull(itemService.getById(9999L))
    }

    @Test
    internal fun shouldThrowExceptionWhenInsertDuplicateItem() {
        val item = Item(Random.nextLong(), ITEM_DESCRIPTION)
        itemService.insertOne(item)
        assertThrows<DuplicateItemException> { itemService.insertOne(item) }
    }

    @Test
    internal fun shouldReturnListOfItemsWhenInsertSomeItems() {
        itemService.insertOne(Item(Random.nextLong(), ITEM_DESCRIPTION))
        itemService.insertOne(Item(Random.nextLong(), ITEM_DESCRIPTION))

        assertThat(itemService.getAll().size).isEqualTo(2)
    }

    @Test
    internal fun shouldReturnEmptyListWhenInsertNoItems() {
        assertThat(itemService.getAll().size).isEqualTo(0)
    }
}