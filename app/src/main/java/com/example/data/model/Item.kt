package com.example.data.model

data class Item(
    val title: String,
    val quantity: Int = 1,
    val currency_id: String = "BRL",
    val unit_price: Float
)
