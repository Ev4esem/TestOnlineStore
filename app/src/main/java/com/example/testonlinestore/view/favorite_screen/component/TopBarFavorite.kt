package com.example.testonlinestore.view.favorite_screen.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.testonlinestore.R


@Composable
fun TopBarFavorite() {
    // todo Не возвращает назад
    Row() {
        Icon(
            imageVector = Icons.Filled.ArrowBack,
            contentDescription = stringResource(R.string.exit)
        )
        Spacer(modifier = Modifier.width(28.dp))
        Text(
            text = stringResource(R.string.favorite),
            fontSize = 16.sp
        )
    }


}