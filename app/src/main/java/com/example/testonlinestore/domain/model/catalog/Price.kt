package com.example.testonlinestore.domain.model.catalog

data class Price(
    val discount: Int,
    val price: String,
    val priceWithDiscount: String,
    val unit: String
)