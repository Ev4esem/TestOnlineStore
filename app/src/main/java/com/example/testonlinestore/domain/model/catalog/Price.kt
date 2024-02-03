package com.example.testonlinestore.domain.model.catalog

import androidx.room.Entity

data class Price(
    val discount: Int,
    val price: String,
    val priceWithDiscount: String,
    val unit: String
)