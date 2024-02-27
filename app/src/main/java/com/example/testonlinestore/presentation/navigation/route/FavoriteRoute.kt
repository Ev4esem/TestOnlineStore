package com.example.testonlinestore.presentation.navigation.route

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.testonlinestore.utils.ObserveEffect
import com.example.testonlinestore.view.details_screen.DetailsScreen
import com.example.testonlinestore.view.favorite_screen.FavoriteEffect
import com.example.testonlinestore.view.favorite_screen.FavoriteEvent
import com.example.testonlinestore.view.favorite_screen.FavoriteScreen
import com.example.testonlinestore.view.favorite_screen.FavoriteViewModel

@Composable
fun FavoriteRoute(
    navController : NavController
) {

    val context = LocalContext.current
    val viewModel : FavoriteViewModel = hiltViewModel()

    val uiState by viewModel.productsUiState.collectAsStateWithLifecycle()

    ObserveEffect(viewModel.effectFlow) { effect ->
        when(effect) {
            is FavoriteEffect.ShowToast -> {
                Toast.makeText(context, effect.message, Toast.LENGTH_LONG).show()
            }
        }
    }

    BackHandler(
        uiState.selectedProduct != null
    ) {

        viewModel.obtainEvent(
            FavoriteEvent.ClearSelectedCatalog
        )

    }



    FavoriteScreen(
        onClickBack = {
            navController.popBackStack()
        },
        uiState = uiState,
        onEvent = viewModel::obtainEvent
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
                    FavoriteEvent.ChangeFavoriteDetail(selectedCatalog)
                )
            },
            onClickBack = {
                viewModel.obtainEvent(FavoriteEvent.ClearSelectedCatalog)
            },
            onClickRetry = {
                viewModel.obtainEvent(FavoriteEvent.RefreshProductDetail(selectedCatalog.id))
            },
            imageProducts = uiState.imageProducts
        )

        }
    }
