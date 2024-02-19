package com.example.testonlinestore.view.catalog_screen.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.testonlinestore.R

@Composable
fun TagItem(
    tag : String,
    selected : Boolean,
    onTagClicked : (String) -> Unit
) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .background(
                color = colorResource(if (selected) R.color.dark_blue else R.color.light_grey_background),
                shape = RoundedCornerShape(20.dp)
            )

    ) {

        Text(
            text = tag,
            modifier = Modifier
                .clickable { onTagClicked(tag) }
                .padding(start = 10.dp, end = 10.dp),
            color = colorResource(if (selected) R.color.white else R.color.grey),
            fontSize = 14.sp,
            fontWeight = if (selected) FontWeight.Light else FontWeight.Bold

        )
        if (selected) {
            Icon(
                imageVector = Icons.Filled.Close,
                contentDescription = "",
                tint = colorResource(R.color.white),
                modifier = Modifier
                    .height(15.dp)
                    .padding(end = 10.dp)
            )
        }

    }

}