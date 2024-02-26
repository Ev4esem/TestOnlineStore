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


    val refreshKey = remember {
        mutableStateOf(0)
    }

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
                refreshKey.value++
            }

            FilterButton()

        }

        onEvent(CatalogEvent.SelectedTag(
            tags = uiState.tags,
            tag = uiState.selectedTag
        ))

//        TagList(
//            tags = uiState.tags,
//            selectedTag = uiState.selectedTag,
//            onTagSelected = { selectedTag ->
//                uiState.selectedTag = selectedTag
//            }
//        )

    //    if (!uiState.catalogLoading && !uiState.favoriteCatalogLoading && uiState.products.isEmpty()) {
            ListProduct(
                catalogItems = uiState.products,
                onEvent = onEvent,
                navController = navController
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
                onClickFavorite = { onEvent(CatalogEvent.ChangeFavoriteDetail(selectedProduct)) }
            )
        }


  //  }

}