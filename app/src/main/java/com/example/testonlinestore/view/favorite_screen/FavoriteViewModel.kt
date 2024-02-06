package com.example.testonlinestore.view.favorite_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testonlinestore.domain.model.catalog.CatalogList
import com.example.testonlinestore.domain.model.favorite.CardItem
import com.example.testonlinestore.presentation.use_case.favorite.AddCardItemUseCase
import com.example.testonlinestore.presentation.use_case.favorite.DeleteCardItemUseCase
import com.example.testonlinestore.presentation.use_case.favorite.GetItemCardListUseCase
import com.example.testonlinestore.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val addCardItemUseCase : AddCardItemUseCase,
    private val deleteCardItemUseCase : DeleteCardItemUseCase,
    private val getItemCardListUseCase : GetItemCardListUseCase
) : ViewModel() {

    private var _cardProducts = MutableStateFlow<List<CardItem>>(listOf())
    val cardProducts : StateFlow<List<CardItem>> = _cardProducts

    init {
        getCardProducts()
    }

    private fun getCardProducts() {
        viewModelScope.launch(Dispatchers.IO) {

            val cardItems = getItemCardListUseCase()
            _cardProducts.value = cardItems
        }
    }

    fun insertCardItem(cardItem : CardItem) {
        viewModelScope.launch {
            addCardItemUseCase(cardItem)
        }
    }

    fun deleteCardItem(cardItem : CardItem) {
        viewModelScope.launch {
            deleteCardItemUseCase(cardItem)
        }
    }


}