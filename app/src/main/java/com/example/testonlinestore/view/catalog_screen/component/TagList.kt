package com.example.testonlinestore.view.catalog_screen.component

import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable


// todo Неработает, когда я ее устанавливаю и
//  товары не отображаются, и теги не отображаются
@Composable
fun TagList(
    tags : List<String>,
    selected : Set<String>,
    onTagClicked : (String) -> Unit
) {

    LazyRow() {
        items(tags) { tag ->
            TagItem(tag = tag, selected = selected.contains(tag)) {
                onTagClicked(it)
            }
        }
    }

}