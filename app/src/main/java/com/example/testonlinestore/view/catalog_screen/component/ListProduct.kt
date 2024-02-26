package com.example.testonlinestore.view.catalog_screen.component

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.testonlinestore.domain.model.catalog.Catalog
import com.example.testonlinestore.domain.model.catalog.CatalogList
import com.example.testonlinestore.presentation.navigation.Screen
import com.example.testonlinestore.view.catalog_screen.CatalogEvent

@Composable
fun ListProduct(
    modifier : Modifier = Modifier,
    catalogItems : List<Catalog>,
    onEvent : (CatalogEvent) -> Unit,
    navController : NavController
) {

    val listState = rememberLazyGridState()

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        state = listState,

        content = {
            items(
                items = catalogItems,
                key = Catalog::id
            ) { catalog ->
                Item(
                    title = catalog.title,
                    subtitle = catalog.subtitle,
                    price = catalog.price.price,
                    priceWithDiscount = catalog.price.priceWithDiscount,
                    unit = catalog.price.unit,
                    rating = catalog.feedback.rating,
                    count = catalog.feedback.count,
                    discount = catalog.price.discount,
                    onItemClick = {
                        onEvent(CatalogEvent.SelectedProduct(catalog.id))
                        // todo
                        navController.navigate(Screen.DetailsScreen.route)
                    },
                    onClickFavorite = {
                        onEvent(CatalogEvent.ChangeFavorite(catalog))
                    },
                    isFavorite = catalog.favorite
                )
            }
        },

        )

}