package com.example.testonlinestore.view.details_screen.component

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.testonlinestore.R


//todo Не работает кнопка
@Composable
fun FavoriteIcon() {

    IconButton(
        onClick = { /*TODO*/ }
    ) {
        Icon(
            painter = painterResource(R.drawable.heart_icon),
            contentDescription = stringResource(R.string.favorite),
            tint = colorResource(id = R.color.pink)
        )
    }

}
