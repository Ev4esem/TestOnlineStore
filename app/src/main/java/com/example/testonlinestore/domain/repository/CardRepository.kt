package com.example.testonlinestore.domain.repository

import com.example.testonlinestore.data.database.models.CardItem
import com.example.testonlinestore.domain.model.catalog.Catalog
import kotlinx.coroutines.flow.Flow

interface CardRepository {

     fun getAllListCardItem() : Flow<List<Catalog>>


}