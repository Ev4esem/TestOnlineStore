package com.example.testonlinestore.view.catalog_screen.component

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.testonlinestore.domain.model.catalog.CatalogList
import com.example.testonlinestore.presentation.navigation.Screen

@Composable
fun ListProduct(
    catalogItems : CatalogList,
    navController : NavController,
    key : Int
) {

    val listState = rememberLazyGridState()

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        state = listState,

        content = {
            items(
                items = catalogItems.items,
                key = { catalog -> catalog.id + key }
            ) { catalog ->
                Item(
                    id = catalog.id,
                    title = catalog.title,
                    subtitle = catalog.subtitle,
                    price = catalog.price.price,
                    priceWithDiscount = catalog.price.priceWithDiscount,
                    unit = catalog.price.unit,
                    rating = catalog.feedback.rating,
                    count = catalog.feedback.count,
                    discount = catalog.price.discount,
                    onItemClick = { navController.navigate(Screen.DetailsScreen.route + "/${catalog.id}") }
                )
            }
        },

        )

}