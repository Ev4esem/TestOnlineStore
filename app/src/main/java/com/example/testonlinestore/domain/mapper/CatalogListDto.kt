package com.example.testonlinestore.domain.mapper

import com.google.gson.annotations.SerializedName

data class CatalogListDto(
    @SerializedName("items")
    val items : List<CatalogDto>
)
