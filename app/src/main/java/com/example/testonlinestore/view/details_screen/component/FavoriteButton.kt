package com.example.testonlinestore.view.details_screen.component

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.testonlinestore.R



@Composable
fun FavoriteButton(
    onClickFavorite : () -> Unit,
    isFavorite : Boolean,
    modifier : Modifier = Modifier
) {
    IconButton(
        onClick = onClickFavorite
    ) {
        Icon(
            painter = painterResource(
                if (isFavorite) {
                    R.drawable.heart_fill_icon
                } else {
                    R.drawable.heart_icon
                }
            ),
            contentDescription = stringResource(R.string.favorite),
            tint = colorResource(id = R.color.pink)
        )
    }

}
