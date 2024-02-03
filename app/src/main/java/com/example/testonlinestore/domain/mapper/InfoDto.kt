package com.example.testonlinestore.domain.mapper

import com.example.testonlinestore.domain.model.catalog.Info
import com.google.gson.annotations.SerializedName

data class InfoDto(
    @SerializedName("title")
    val title : String,
    @SerializedName("value")
    val value : String
)

fun InfoDto.toInfo() : Info = Info(
    title = title,
    value = value
)

fun List<InfoDto>.toInfoList() : List<Info> {
    return map { it.toInfo() }
}

