package com.example.testonlinestore.view.details_screen.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.sp
import com.example.testonlinestore.R

@Composable
fun CountProduct(
    count : Int
) {

    val nameCount = when {
        count == 0 -> "штук"
        count == 1 -> "штука"
        count < 4 -> "штуки"
        else -> "штук"
    }
    val nameState by remember {
        mutableStateOf(nameCount)
    }

    Text(
        text = "Доступно для заказа $count $nameState ",
        fontSize = 12.sp,
        color = colorResource(R.color.grey),
        modifier = Modifier.fillMaxWidth()
    )

}