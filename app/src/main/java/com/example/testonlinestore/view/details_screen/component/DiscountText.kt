package com.example.testonlinestore.view.details_screen.component

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.testonlinestore.R

@Composable
fun DiscountText(
    discount : Int
) {


    Surface(
        color = colorResource(R.color.pink),
        modifier = Modifier
            .padding(top = 4.dp)
            .clip(RoundedCornerShape(5.dp))
    ) {

        Text(
            text = "-$discount%",
            color = colorResource(R.color.white),
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(start = 7.dp, end = 7.dp)
        )

    }


}