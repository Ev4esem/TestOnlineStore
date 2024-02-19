package com.example.testonlinestore.view.details_screen.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.testonlinestore.R

@Composable
fun AddProductButton(
    price : String,
    priceWithDiscount : String,
    unit : String
) {

    Card(
        colors = CardDefaults.cardColors(
            containerColor = colorResource(R.color.pink)
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(51.dp)
            ,
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically

        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(start = 16.dp)
            ) {
                Text(
                    text = "$price$unit",
                    color = colorResource(R.color.white),
                    fontSize = 14.sp
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    text = "$priceWithDiscount $unit",
                    style = MaterialTheme.typography.bodySmall,
                    color = colorResource(R.color.grey),
                    fontSize = 10.sp,
                    textDecoration = TextDecoration.LineThrough
                )

            }

            Text(
                text = stringResource(R.string.add_card),
                color = colorResource(R.color.white),
                fontSize = 14.sp,
                modifier = Modifier.padding(end = 16.dp)
            )

        }




    }


}