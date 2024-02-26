package com.example.testonlinestore.data.database.converter

import androidx.room.TypeConverter
import com.example.testonlinestore.domain.model.catalog.Tags
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class TagsConverter {

    @TypeConverter
    fun fromTags(tags : List<String>) : String {

        return Gson().toJson(tags)

    }

    @TypeConverter
    fun toTags(tags : String) : List<String> {
        val type = object : TypeToken<Tags>() {}.type
        return Gson().fromJson(tags,type)
    }


}
