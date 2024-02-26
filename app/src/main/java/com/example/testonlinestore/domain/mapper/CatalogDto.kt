package com.example.testonlinestore.domain.mapper

import com.example.testonlinestore.domain.model.catalog.Catalog
import com.google.gson.annotations.SerializedName


data class CatalogDto(

    @SerializedName("feedback")
    val feedback: FeedbackDto,
    @SerializedName("id")
    val id: String,
    @SerializedName("price")
    val price: PriceDto,
    @SerializedName("subtitle")
    val subtitle: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("tags")
    val tagsDto : List<String>
)

fun CatalogDto.toCatalogProduct(favorite : Boolean)  = Catalog(
    feedback = feedback.toFeedback(),
    id = id,
    price = price.toPrice(),
    subtitle = subtitle,
    title = title,
    favorite = favorite,
    tags = tagsDto
)



