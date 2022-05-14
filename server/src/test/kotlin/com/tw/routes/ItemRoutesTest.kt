package com.tw.routes

import com.tw.entity.Item
import com.tw.exception.DuplicateItemException
import com.tw.service.ItemService
import com.tw.utility.Constants.API_PATH
import com.tw.utility.configure
import com.tw.utility.configureClient
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.server.testing.*
import io.mockk.coEvery
import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import kotlin.random.Random

private const val ITEM_DESCRIPTION = "Harry Potter"

class ItemRoutesTest {

    private val itemService = mockk<ItemService>()

    @Test
    internal fun `insert one item`() = testApplication {
        this.configure(itemService)
        val client = this.configureClient()
       val item = Item(Random.nextLong(), ITEM_DESCRIPTION)

       coEvery { itemService.insertOne(any()) } returns Unit

       val response = client.post("$API_PATH/items") {
           contentType(ContentType.Application.Json)
           setBody(item)
       }

       assertThat(response.status).isEqualTo(HttpStatusCode.Created)
   }

    @Test
    internal fun `insert duplicate item`() = testApplication {
        this.configure(itemService)
        val client = this.configureClient()
        val item = Item(Random.nextLong(), ITEM_DESCRIPTION)

        coEvery { itemService.insertOne(any()) } throws(DuplicateItemException("Item already exists"))

        val response = client.post("$API_PATH/items") {
            contentType(ContentType.Application.Json)
            setBody(item)
        }

        assertThat(response.status).isEqualTo(HttpStatusCode.Conflict)
    }

    @Test
    internal fun `get one item`() = testApplication {
        this.configure(itemService)
        val client = this.configureClient()

        coEvery { itemService.getById(any()) } returns Item(1L, ITEM_DESCRIPTION)

        val response = client.get("$API_PATH/items/1")

        assertThat(response.status).isEqualTo(HttpStatusCode.OK)
    }

    @Test
    internal fun `get item that does not exists`() = testApplication {
        this.configure(itemService)
        val client = this.configureClient()

        coEvery { itemService.getById(any()) } returns null

        val response = client.get("$API_PATH/items/1")

        assertThat(response.status).isEqualTo(HttpStatusCode.NotFound)
    }

    @Test
    internal fun `get all items`() = testApplication {
        this.configure(itemService)
        val client = this.configureClient()

        coEvery { itemService.getAll() } returns listOf(Item(1L, ITEM_DESCRIPTION))

        val response = client.get("$API_PATH/items")

        assertThat(response.status).isEqualTo(HttpStatusCode.OK)
    }
}