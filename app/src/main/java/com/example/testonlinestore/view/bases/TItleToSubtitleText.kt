package com.example.testonlinestore.view.bases

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.testonlinestore.R


@Composable
fun TitleTextToSubtitleText(
    title : String,
    subtitle : String
) {
    Column {
        Text(
            text = title,
            color = colorResource(id = R.color.grey),
            fontSize = 16.sp
        )

        Text(
            text = subtitle,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

    }




}