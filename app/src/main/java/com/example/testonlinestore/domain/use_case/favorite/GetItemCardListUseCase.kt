package com.example.testonlinestore.domain.use_case.favorite

import com.example.testonlinestore.data.database.models.CardItem
import com.example.testonlinestore.domain.model.catalog.Catalog
import com.example.testonlinestore.domain.repository.CardRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetItemCardListUseCase @Inject constructor(
    private val cardRepository : CardRepository
) {

     operator fun invoke() : Flow<List<Catalog>> {
        return cardRepository.getAllListCardItem()
    }

}