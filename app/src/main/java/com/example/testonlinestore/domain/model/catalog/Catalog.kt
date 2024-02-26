package com.example.testonlinestore.domain.model.catalog

import com.example.testonlinestore.data.database.models.CardItem

data class Catalog(
    val feedback: Feedback,
    val id: String,
    val price: Price,
    val subtitle: String,
    val title: String,
    val favorite : Boolean,
    val tags : List<String>
)

fun Catalog.toCardItem(favorite : Boolean) = CardItem(
    id = id,
    price = price,
    feedback = feedback,
    subtitle = subtitle,
    title = title,
    favorite = favorite,
    tags = tags
)