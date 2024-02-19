package com.example.testonlinestore.view.catalog_screen.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.testonlinestore.R
import com.example.testonlinestore.utils.SortOption


@Composable
fun SortButton(
    onSortSelected: (SortOption) -> Unit
) {

    var expanded by remember {
        mutableStateOf(false)
    }
    var selectedOption by remember {
        mutableStateOf(SortOption.POPULARITY)
    }

    Box(
        modifier = Modifier.padding(start = 16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(R.drawable.sort_action_icon),
                contentDescription = stringResource(R.string.sort)
            )
            Spacer(modifier = Modifier.width(9.dp))
            Text(
                text = when(selectedOption) {
                    SortOption.POPULARITY -> stringResource(R.string.popularity)
                    SortOption.PRICE_LOW_TO_HIGH -> stringResource(R.string.price_low_to_high)
                    SortOption.PRICE_HIGH_TO_LOW -> stringResource(R.string.price_high_to_low)
                },
                modifier = Modifier.clickable { expanded = true }
            )
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {

                DropdownMenuItem(
                    onClick = {
                        selectedOption = SortOption.POPULARITY
                        onSortSelected(selectedOption)
                        expanded = false
                    },
                ) {
                    Text(text = stringResource(R.string.popularity))
                }
                DropdownMenuItem(
                    onClick = {
                        selectedOption = SortOption.PRICE_LOW_TO_HIGH
                        onSortSelected(selectedOption)
                        expanded = false
                    },
                ) {
                    Text(text = stringResource(R.string.price_low_to_high))
                }

                DropdownMenuItem(
                    onClick = {
                        selectedOption = SortOption.PRICE_HIGH_TO_LOW
                        onSortSelected(selectedOption)
                        expanded = false
                    },
                ) {
                    Text(text = stringResource(R.string.price_high_to_low))
                }

            }
        }
    }

}