package com.tw.models

import com.fasterxml.jackson.annotation.JsonProperty

data class Item(@JsonProperty("description") val description: String) {
}