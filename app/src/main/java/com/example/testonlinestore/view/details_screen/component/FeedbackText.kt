package com.example.testonlinestore.view.details_screen.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.testonlinestore.R


@Composable
fun FeedbackText(
    rating : Int,
    count : Int
) {
    val nameFeedback = when {
        count == 0 -> "отзывов"
        count == 1 -> "отзыв"
        count < 4 -> "штуки"
        else -> "отзывов"
    }
    val nameState by remember {
        mutableStateOf(nameFeedback)
    }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        RatingBar(rating = rating)
        Spacer(modifier = Modifier.width(5.dp))
        Text(
            text = "$rating",
            color = colorResource(id = R.color.grey),
            fontSize = 12.sp
        )
        Spacer(modifier = Modifier.width(5.dp))
        Text(
            text = "* $count $nameState",
            color = colorResource(id = R.color.grey),
            fontSize = 12.sp
        )
    }

}
@Composable
fun RatingBar(rating: Int) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        repeat(5) { index ->
            val isFilled = index < rating
            Star(isFilled)
        }
    }
}

@Composable
fun Star(isFilled: Boolean) {
    val starColor = if (isFilled) R.color.orange else R.color.grey
    Icon(
        painter = painterResource(id = R.drawable.star),
        contentDescription = null,
        tint = colorResource(starColor)
    )
}