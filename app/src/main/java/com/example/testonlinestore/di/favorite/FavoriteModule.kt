package com.example.testonlinestore.di.favorite

import com.example.testonlinestore.data.repositories.CardRepositoryImpl
import com.example.testonlinestore.domain.repository.CardRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface FavoriteModule {

    @Binds
    fun cardRepositoryImpl_to_toCardRepository(
        cardRepositoryImpl : CardRepositoryImpl
    ) : CardRepository

}