package com.example.testonlinestore.presentation.navigation.route

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.testonlinestore.utils.ObserveEffect
import com.example.testonlinestore.view.catalog_screen.CatalogEffect
import com.example.testonlinestore.view.catalog_screen.CatalogEvent
import com.example.testonlinestore.view.catalog_screen.CatalogScreen
import com.example.testonlinestore.view.catalog_screen.CatalogViewModel
import com.example.testonlinestore.view.details_screen.DetailsScreen

@Composable
fun CatalogRoute(
    modifier : Modifier = Modifier,
    navController : NavController
) {

    val context = LocalContext.current
    val viewModel : CatalogViewModel = hiltViewModel()
    val uiState by viewModel.productsUiState.collectAsStateWithLifecycle()


    ObserveEffect(viewModel.effectFlow) { effect ->
        when(effect) {
            is CatalogEffect.ShowToast -> {
                Toast.makeText(context, effect.message, Toast.LENGTH_LONG).show()
            }
        }
    }


    BackHandler(
        uiState.selectedProduct != null
    ) {
        viewModel.obtainEvent(
            CatalogEvent.ClearSelectedCatalog
        )
    }

    CatalogScreen(
        uiState = uiState,
        onEvent = viewModel::obtainEvent,
        navController = navController
        )

    uiState.selectedProduct?.let { selectedCatalog ->

        DetailsScreen(

            available = selectedCatalog.available,
            description = selectedCatalog.description,
            feedback = selectedCatalog.feedback,
            info = selectedCatalog.info,
            ingredients = selectedCatalog.ingredients,
            price = selectedCatalog.price,
            subtitle = selectedCatalog.subtitle,
            title = selectedCatalog.title,
            loading = uiState.catalogDetailLoading,
            error = uiState.error,
            isFavorite = selectedCatalog.favorite,
            onClickFavorite = {
                  viewModel.obtainEvent(
                      CatalogEvent.ChangeFavoriteDetail(selectedCatalog)
                  )
            },
            onClickBack = {
                viewModel.obtainEvent(CatalogEvent.ClearSelectedCatalog)
            },
            onClickRetry = {
                viewModel.obtainEvent(CatalogEvent.RefreshProductDetail(selectedCatalog.id))
            },
            imageProducts = uiState.imageProducts
        )

    }



}