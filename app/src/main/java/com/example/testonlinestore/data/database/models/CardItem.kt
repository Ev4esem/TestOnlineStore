package com.example.testonlinestore.data.database.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.testonlinestore.domain.model.catalog.Catalog
import com.example.testonlinestore.domain.model.catalog.Feedback
import com.example.testonlinestore.domain.model.catalog.Price
import com.example.testonlinestore.domain.model.catalog.Tags

@Entity(tableName = "card_item")
data class CardItem(
   @PrimaryKey val id: String,
    val price : Price,
    val feedback : Feedback,
    val subtitle: String,
    val title: String,
    val favorite : Boolean,
    val tags : List<String> = listOf()
)

fun CardItem.toCatalog() = Catalog(
   id = id,
   title = title,
   subtitle = subtitle,
   feedback = feedback,
   price = price,
    favorite = favorite,
    tags = tags
)

