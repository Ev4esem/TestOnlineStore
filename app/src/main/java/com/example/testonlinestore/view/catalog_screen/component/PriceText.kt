package com.example.testonlinestore.view.catalog_screen.component

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable


@Composable
fun PriceText(
    price : String,
    unit : String
) {

    Text(
        text = "$price $unit",
        style = MaterialTheme.typography.titleMedium
    )

}