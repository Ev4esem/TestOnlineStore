package com.example.testonlinestore.view.catalog_screen.component

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.sp
import com.example.testonlinestore.R

@Composable
fun DescriptionItemText(
    title : String,
    subtitle : String
) {

    Column {

        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            fontSize = 12.sp
        )
        Text(
            text = subtitle,
            style = MaterialTheme.typography.bodySmall,
            fontSize = 10.sp,
            color = colorResource(R.color.dark_grey)
        )

    }

}