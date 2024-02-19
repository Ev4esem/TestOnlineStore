package com.example.testonlinestore.view.profile_screen.component

import androidx.annotation.ColorRes
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import com.example.testonlinestore.R

@Composable
fun DataUserText(
    title : String,
    style : TextStyle = MaterialTheme.typography.bodySmall,
    @ColorRes color : Int = R.color.black
) {

    Text(
        text = title,
        style = style,
        color = colorResource(color)
    )

}