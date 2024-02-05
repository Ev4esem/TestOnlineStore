package com.example.testonlinestore.domain.mapper

import com.example.testonlinestore.domain.model.catalog.CatalogList
import com.google.gson.annotations.SerializedName

data class CatalogListDto(
    @SerializedName("items")
    val items : List<CatalogDto>
)

fun CatalogListDto.toCatalogListProduct() : CatalogList = CatalogList(
    items.map { it.toCatalogProduct() }
)
