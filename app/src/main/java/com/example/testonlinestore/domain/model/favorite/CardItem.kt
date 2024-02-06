package com.example.testonlinestore.domain.model.favorite

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.testonlinestore.domain.model.catalog.Feedback
import com.example.testonlinestore.domain.model.catalog.Info
import com.example.testonlinestore.domain.model.catalog.Price

@Entity(tableName = "card_item")
data class CardItem(
   @PrimaryKey val id: String,
    val count: Int,
    val rating: Double,
    val discount: Int,
    val price: String,
    val priceWithDiscount: String,
    val unit: String,
    val subtitle: String,
    val title: String,
)
