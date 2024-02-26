package com.example.testonlinestore.domain.model.catalog

import com.example.testonlinestore.data.database.models.CardItem

data class CatalogDetail(
    val available: Int,
    val description: String,
    val feedback: Feedback,
    val id: String,
    val info: List<Info>,
    val ingredients: String,
    val price: Price,
    val subtitle: String,
    val title: String,
    val favorite : Boolean
)
fun CatalogDetail.toCardItem(favorite : Boolean) = CardItem(
    id = id,
    price = price,
    feedback = feedback,
    subtitle = subtitle,
    title = title,
    favorite = favorite,
)