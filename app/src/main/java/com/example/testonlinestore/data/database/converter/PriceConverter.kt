package com.example.testonlinestore.data.database.converter

import androidx.room.TypeConverter
import com.example.testonlinestore.domain.model.catalog.Price
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class PriceConverter {

    @TypeConverter
    fun fromPrice(price : Price) : String {
        return Gson().toJson(price)
    }

    @TypeConverter
    fun toPrice(price : String) : Price {
        val type = object : TypeToken<Price>() {}.type
        return Gson().fromJson(price,type)
    }


}