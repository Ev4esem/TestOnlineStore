package com.example.testonlinestore.view.catalog_screen.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.testonlinestore.R


@Composable
fun FilterButton() {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(end = 16.dp)
    ){
        Icon(
            painter = painterResource(R.drawable.filter_icon),
            contentDescription = stringResource(R.string.filters)
        )

        Spacer(modifier = Modifier.width(5.dp))
        Text(text = stringResource(R.string.filters))
    }

}