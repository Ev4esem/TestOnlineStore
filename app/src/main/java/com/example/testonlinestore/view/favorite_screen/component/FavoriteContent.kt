package com.example.testonlinestore.view.favorite_screen.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.testonlinestore.R
import com.example.testonlinestore.view.catalog_screen.CatalogEvent
import com.example.testonlinestore.view.details_screen.DetailsScreen
import com.example.testonlinestore.view.favorite_screen.FavoriteEvent
import com.example.testonlinestore.view.favorite_screen.FavoriteUiState


@Composable
fun FavoriteContent(
    onClickBack : () -> Unit,
    onEvent : (FavoriteEvent) -> Unit,
    uiState : FavoriteUiState
) {
    val tabItems = listOf(
        stringResource(id = R.string.items),
        stringResource(id = R.string.brand)
    )
    Column(
        modifier = Modifier.padding(21.dp)
    ) {
        TopBarFavorite(
            onClickBack = onClickBack
        )
        TabLayout(tabs = tabItems)
        if (!uiState.productLoading && uiState.favoriteList.isEmpty()) {

        ListItemFavorite(
                catalog = uiState.favoriteList,
                onEvent = onEvent
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
                    onEvent(FavoriteEvent.ClearSelectedCatalog)
                },
                onClickRetry = {
                    onEvent(FavoriteEvent.RefreshProductDetail(selectedProduct.id))
                },
                isFavorite = selectedProduct.favorite,
                onClickFavorite = { onEvent(FavoriteEvent.ChangeFavoriteDetail(selectedProduct)) }
            )
        }
    }

}



@Preview
@Composable
fun PrevFavoriteScreen() {



}