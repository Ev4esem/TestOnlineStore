package com.example.testonlinestore.view.catalog_screen.component

import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.example.testonlinestore.domain.model.catalog.Catalog
import com.example.testonlinestore.view.catalog_screen.CatalogUiState


@Composable
fun TagList(
    tags : List<Catalog>,
    selectedTag : String,
    onTagSelected : (String) -> Unit
) {

    LazyRow() {
        items(tags) { tag ->
            TagItem(
                tag = selectedTag,
                selected = tag.tags.first() == selectedTag,
                onTagClicked = { selectedTag -> onTagSelected(selectedTag) }
            )
        }
    }

}