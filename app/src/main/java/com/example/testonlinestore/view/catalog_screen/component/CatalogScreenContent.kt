package com.example.testonlinestore.view.catalog_screen.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.testonlinestore.view.bases.StoreEmptyList
import com.example.testonlinestore.view.bases.StoreErrorScreen
import com.example.testonlinestore.view.bases.StoreLoadingScreen
import com.example.testonlinestore.view.catalog_screen.CatalogEvent
import com.example.testonlinestore.view.catalog_screen.CatalogUiState
import com.example.testonlinestore.view.catalog_screen.CatalogViewModel
import com.example.testonlinestore.view.details_screen.DetailsScreen

@Composable
fun CatalogScreenContent(
    uiState : CatalogUiState,
    onEvent : (CatalogEvent) -> Unit,
    navController : NavController
) {


    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween

        ) {

            SortButton { sortOption ->
                onEvent(
                    CatalogEvent.SelectSortCatalog(sortOption)
                )
            }

            FilterButton()

        }

        onEvent(CatalogEvent.SelectedTag(
            tags = uiState.tags,
            tag = uiState.selectedTag
        ))

        TagList(
            tags = uiState.tags,
            selectedTag = uiState.selectedTag,
            onTagSelected = { selectedTag ->
                uiState.selectedTag = selectedTag
            }
        )

        if (!uiState.error.isNullOrBlank()) {
            StoreErrorScreen(
                errorText = uiState.error.toString(),
                onClickRetry = {
                    onEvent(CatalogEvent.RefreshData)
                }
            )
        } else if (uiState.catalogLoading) {
            StoreLoadingScreen()
        } else if (!uiState.catalogLoading && uiState.products.isEmpty()) {
            StoreEmptyList()
        } else {
            ListProduct(
                catalogItems = uiState.products,
                onEvent = onEvent,
                navController = navController,
                imageProducts = uiState.imageProducts,
                sortOption = uiState.sortOption
            )
        }
        uiState.selectedProduct?.let { selectedProduct ->
            DetailsScreen(
                available = selectedProduct.available,
                description = selectedProduct.description,
                feedback = selectedProduct.feedback,
                info = selectedProduct.info,
                ingredients = selectedProduct.ingredients,
                price = selectedProduct.price,
                subtitle = selectedProduct.subtitle,
                title = selectedProduct.title,
                loading = uiState.catalogDetailLoading,
                error = uiState.errorCatalogDetail,
                onClickBack = {
                    onEvent(CatalogEvent.ClearSelectedCatalog)
                },
                onClickRetry = {
                    onEvent(CatalogEvent.RefreshProductDetail(selectedProduct.id))
                },
                isFavorite = selectedProduct.favorite,
                onClickFavorite = { onEvent(CatalogEvent.ChangeFavoriteDetail(selectedProduct)) },
                imageProducts = uiState.imageProducts
            )
        }


    }

}