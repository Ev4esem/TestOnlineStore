package com.example.testonlinestore.data.database.converter

import androidx.room.TypeConverter
import com.example.testonlinestore.domain.model.catalog.Feedback
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class FeedbackConverters {

    @TypeConverter
    fun fromFeedback(feedback : Feedback) : String {
        return Gson().toJson(feedback)
    }

    @TypeConverter
    fun toFeedback(feedback : String) : Feedback {
        val type = object : TypeToken<Feedback>() {}.type
        return Gson().fromJson(feedback,type)
    }


}