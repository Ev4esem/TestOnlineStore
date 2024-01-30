package com.example.testonlinestore.domain.model.favorite

import com.example.testonlinestore.domain.model.catalog.Feedback
import com.example.testonlinestore.domain.model.catalog.Info
import com.example.testonlinestore.domain.model.catalog.Price

data class CardItem(
    val available: Int,
    val description: String,
    val feedback: Feedback,
    val id: String,
    val info: List<Info>,
    val ingredients: String,
    val price: Price,
    val subtitle: String,
    val title: String
)
