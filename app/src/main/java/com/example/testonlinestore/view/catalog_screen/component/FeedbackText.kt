package com.example.testonlinestore.view.catalog_screen.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.testonlinestore.R

@Composable
fun FeedbackText(
    modifier : Modifier = Modifier,
    count : Int,
    rating : Double
) {

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(
            painter = painterResource(R.drawable.star),
            contentDescription = "Feedback",
            tint = colorResource(id = R.color.orange),
            modifier = modifier.padding(end = 2.dp)
        )

        Text(
            text = "$rating",
            color = colorResource(R.color.orange),
            fontSize = 9.sp,
            modifier = modifier.padding(end = 2.dp)
        )

        Text(
            text = "($count)",
            color = colorResource(R.color.light_grey_component),
            fontSize = 9.sp
        )


    }

}