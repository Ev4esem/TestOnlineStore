package com.example.testonlinestore.view.favorite_screen.component

import android.annotation.SuppressLint
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.testonlinestore.view.bases.ItemFavorite
import com.example.testonlinestore.view.favorite_screen.FavoriteViewModel

@SuppressLint("SuspiciousIndentation")
@Composable
fun ListItemFavorite(
    viewModel : FavoriteViewModel = hiltViewModel()
) {

    val cardItems by viewModel.cardProducts.collectAsState()

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),

        content = {
            items(
                items = cardItems
            ) { catalog ->
                ItemFavorite(cardItem = catalog)
            }
        },
    )

}