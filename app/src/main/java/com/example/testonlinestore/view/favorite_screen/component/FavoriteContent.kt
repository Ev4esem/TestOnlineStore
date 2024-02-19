package com.example.testonlinestore.view.favorite_screen.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun FavoriteContent() {
    //todo Добавить в ресурсы
    val tabItems = listOf("Товары","Бренды")
    Column(
        modifier = Modifier.padding(21.dp)
    ) {
        TopBarFavorite()
        TabLayout(tabs = tabItems)
        ListItemFavorite()
    }

}



@Preview
@Composable
fun PrevFavoriteScreen() {



}