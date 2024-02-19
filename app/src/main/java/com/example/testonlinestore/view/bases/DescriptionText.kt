package com.example.testonlinestore.view.bases

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.sp
import com.example.testonlinestore.R

@Composable
fun DescriptionText(
    description : String
) {

    Text(
        text = description,
        fontSize = 12.sp,
        color = colorResource(R.color.dark_grey),
    )

}