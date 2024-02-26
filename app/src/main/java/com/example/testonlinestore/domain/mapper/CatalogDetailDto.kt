package com.example.testonlinestore.domain.mapper

import com.example.testonlinestore.domain.model.catalog.CatalogDetail
import com.google.gson.annotations.SerializedName

data class CatalogDetailDto(
    @SerializedName("available")
    val available: Int,
    @SerializedName("description")
    val description: String,
    @SerializedName("feedback")
    val feedback: FeedbackDto,
    @SerializedName("id")
    val id: String,
    @SerializedName("info")
    val info: List<InfoDto>,
    @SerializedName("ingredients")
    val ingredients: String,
    @SerializedName("price")
    val price: PriceDto,
    @SerializedName("subtitle")
    val subtitle: String,
    @SerializedName("title")
    val title: String
)

fun CatalogDetailDto.toCatalogDetail(favorite : Boolean) = CatalogDetail(
    available = available,
    description = description,
    feedback = feedback.toFeedback(),
    id = id,
    info = info.toInfoList(),
    ingredients = ingredients,
    price = price.toPrice(),
    subtitle = subtitle,
    title = title,
    favorite = favorite
)
