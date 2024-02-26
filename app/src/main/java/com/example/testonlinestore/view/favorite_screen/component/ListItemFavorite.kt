package com.example.testonlinestore.view.favorite_screen.component

import android.annotation.SuppressLint
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.testonlinestore.domain.model.catalog.Catalog
import com.example.testonlinestore.view.bases.ItemFavorite
import com.example.testonlinestore.view.catalog_screen.CatalogEvent
import com.example.testonlinestore.view.favorite_screen.FavoriteEvent
import com.example.testonlinestore.view.favorite_screen.FavoriteViewModel

@SuppressLint("SuspiciousIndentation")
@Composable
fun ListItemFavorite(
    catalog : List<Catalog>,
    onEvent : (FavoriteEvent) -> Unit
) {


    LazyVerticalGrid(
        columns = GridCells.Fixed(2),

        content = {
            items(
                items = catalog,
                key = Catalog::id
            ) { catalog ->
                ItemFavorite(
                    title = catalog.title,
                    subtitle = catalog.subtitle,
                    price = catalog.price.price,
                    priceWithDiscount = catalog.price.priceWithDiscount,
                    unit = catalog.price.unit,
                    rating = catalog.feedback.rating,
                    count = catalog.feedback.count,
                    discount = catalog.price.discount,
                    onItemClick = {
                        onEvent(FavoriteEvent.SelectedProduct(catalog.id))
                    },
                    onClickFavorite = {
                        onEvent(FavoriteEvent.ChangeFavorite(catalog))
                    },
                    isFavorite = catalog.favorite
                )
            }
        },
    )

}