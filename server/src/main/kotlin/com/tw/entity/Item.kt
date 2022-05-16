package com.tw.entity

import kotlinx.serialization.Serializable
import kotlin.random.Random

@Serializable
data class Item(val id: Long = Random.nextLong(),
                val description: String) {

    constructor() : this(Random.nextLong(), "") {}
}