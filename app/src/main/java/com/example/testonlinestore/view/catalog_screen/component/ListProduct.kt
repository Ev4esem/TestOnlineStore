package com.example.testonlinestore.view.catalog_screen.component

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.testonlinestore.domain.model.ImageProduct
import com.example.testonlinestore.domain.model.catalog.Catalog
import com.example.testonlinestore.domain.model.catalog.CatalogList
import com.example.testonlinestore.presentation.navigation.Screen
import com.example.testonlinestore.utils.SortOption
import com.example.testonlinestore.view.catalog_screen.CatalogEvent
import com.example.testonlinestore.view.catalog_screen.CatalogUiState

@Composable
fun ListProduct(
    catalogItems : List<Catalog>,
    onEvent : (CatalogEvent) -> Unit,
    sortOption : SortOption,
    navController : NavController,
    imageProducts : List<ImageProduct>
) {

    val listState = rememberLazyGridState()

    // todo
    val sort = when(sortOption) {
            SortOption.POPULARITY -> catalogItems.sortedByDescending { it.feedback.rating }
            SortOption.PRICE_LOW_TO_HIGH -> catalogItems.sortedBy { it.price.priceWithDiscount.toDoubleOrNull() ?: Double.MAX_VALUE  }
            SortOption.PRICE_HIGH_TO_LOW -> catalogItems.sortedByDescending { it.price.priceWithDiscount.toDoubleOrNull() ?: Double.MIN_VALUE }
        }


    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        state = listState,

        content = {
            items(
                items = sort,
                key = { catalog -> catalog.id }
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
                    isFavorite = catalog.favorite,
                    imageProducts = imageProducts

                )
            }
        },

        )

}