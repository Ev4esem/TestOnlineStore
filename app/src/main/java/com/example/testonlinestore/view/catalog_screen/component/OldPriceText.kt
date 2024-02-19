package com.example.testonlinestore.view.catalog_screen.component

import androidx.annotation.ColorRes
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.example.testonlinestore.R


@Composable
fun OldPriceText(
    priceWithDiscount : String,
    unit : String,
    style : MaterialTheme = MaterialTheme,
    @ColorRes color : Int = R.color.grey,
    textSize : Int = 9
) {

    Row {
        Text(
            text = "$priceWithDiscount $unit",
            style = style.typography.bodySmall,
            color = colorResource(color),
            fontSize = textSize.sp,
            textDecoration = TextDecoration.LineThrough
        )

    }

}