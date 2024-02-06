package com.example.testonlinestore.domain.di.favorite

import com.example.testonlinestore.domain.repositories.CardRepositoryImpl
import com.example.testonlinestore.presentation.repository.CardRepository
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