package com.example.testonlinestore.view.details_screen.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.testonlinestore.R


@Composable
fun PriceProductText(
    price : String,
    priceWithDiscount : String,
    unit : String
) {

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "$price $unit",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.width(7.dp))
        Text(
            text = "$priceWithDiscount $unit",
            style = MaterialTheme.typography.bodySmall,
            color = colorResource(R.color.grey),
            fontSize = 12.sp,
            textDecoration = TextDecoration.LineThrough
        )

    }


}