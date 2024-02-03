package com.example.testonlinestore.domain.mapper

import com.example.testonlinestore.domain.model.catalog.Feedback
import com.google.gson.annotations.SerializedName

data class FeedbackDto(
    @SerializedName("count")
    val count: Int,
    @SerializedName("rating")
    val rating: Double
)

fun FeedbackDto.toFeedback() : Feedback = Feedback(
    count = count,
    rating = rating
)
