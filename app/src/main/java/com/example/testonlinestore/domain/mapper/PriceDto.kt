package com.example.testonlinestore.domain.mapper

import com.example.testonlinestore.domain.model.catalog.Price
import com.google.gson.annotations.SerializedName

data class PriceDto(
    @SerializedName("discount")
    val discount : Int,
    @SerializedName("price")
    val price: String,
    @SerializedName("priceWithDiscount")
    val priceWithDiscount: String,
    @SerializedName("unit")
    val unit: String

)

fun PriceDto.toPrice() : Price = Price(
    discount = discount,
    price = price,
    priceWithDiscount = priceWithDiscount,
    unit = unit
)
