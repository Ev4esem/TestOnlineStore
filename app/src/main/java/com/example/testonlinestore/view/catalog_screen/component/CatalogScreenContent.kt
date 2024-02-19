package com.example.testonlinestore.view.catalog_screen.component

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.testonlinestore.domain.model.catalog.CatalogList
import com.example.testonlinestore.utils.ProgressBar
import com.example.testonlinestore.utils.Resource
import com.example.testonlinestore.view.catalog_screen.CatalogViewModel

@Composable
fun CatalogScreenContent(
    navController : NavController,
    viewModel : CatalogViewModel
) {

    val tagsState by viewModel.tags.collectAsState()
    val catalogItemsState by viewModel.listProduct.collectAsState()
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
                viewModel.sortCatalogProducts(sortOption)
                refreshKey.value++
            }

            FilterButton()

        }



        when(catalogItemsState) {

            is Resource.Loading -> {
                ProgressBar()
            }
            is Resource.Error -> {
                Log.e("NetworkError", "Catalog items loading failed")
            }
            is Resource.Success -> {

                val catalogItems = (catalogItemsState as? Resource.Success)?.data ?: CatalogList(emptyList())
                ListProduct(catalogItems = catalogItems, navController = navController, key = refreshKey.value)
            }

        }

    }

}