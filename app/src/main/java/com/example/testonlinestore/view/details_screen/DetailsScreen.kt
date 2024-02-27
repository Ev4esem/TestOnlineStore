package com.example.testonlinestore.view.details_screen

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.testonlinestore.domain.model.ImageProduct
import com.example.testonlinestore.domain.model.catalog.Feedback
import com.example.testonlinestore.domain.model.catalog.Info
import com.example.testonlinestore.domain.model.catalog.Price
import com.example.testonlinestore.view.bases.StoreErrorScreen
import com.example.testonlinestore.view.bases.StoreLoadingScreen
import com.example.testonlinestore.view.details_screen.component.ContentDetailsScreen
import com.example.testonlinestore.view.details_screen.component.TopBarDetails

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DetailsScreen(
    available : Int,
    description : String,
    feedback : Feedback,
    info : List<Info>,
    ingredients : String,
    price : Price,
    subtitle : String,
    title : String,
    loading : Boolean,
    error : String?,
    isFavorite : Boolean,
    onClickFavorite : () -> Unit,
    imageProducts : List<ImageProduct>,
    onClickBack : () -> Unit,
    onClickRetry : () -> Unit
) {

    Scaffold(
        topBar = {
        },
        content = {
            if (!error.isNullOrBlank()) {
                StoreErrorScreen(
                    errorText = error,
                    onClickRetry = onClickRetry
                )
            } else if (loading) {
                StoreLoadingScreen()
            } else {
                ContentDetailsScreen(
                    available = available,
                    description = description,
                    feedback = feedback,
                    info = info,
                    ingredients = ingredients,
                    price = price,
                    subtitle = subtitle,
                    title = title,
                    onClickBack = onClickBack,
                    isFavorite = isFavorite,
                    onClickFavorite = onClickFavorite,
                    imageProducts = imageProducts
                )
            }

        },
        bottomBar = {}
    )

}