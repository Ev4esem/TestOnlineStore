package com.example.testonlinestore.presentation.use_case.favorite

import com.example.testonlinestore.domain.model.favorite.CardItem
import com.example.testonlinestore.presentation.repository.CardRepository
import javax.inject.Inject

class AddCardItemUseCase @Inject constructor(
    private val cardRepository : CardRepository
) {

    suspend operator fun invoke(cardItem : CardItem) {
        return cardRepository.addItemCard(cardItem)
    }

}