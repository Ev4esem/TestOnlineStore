package com.example.testonlinestore.domain.di.details

import com.example.testonlinestore.domain.repositories.DetailsRepositoryImpl
import com.example.testonlinestore.presentation.repository.DetailsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DetailsModule {

    @Binds
    fun detailsRepositoryImpl_to_detailsRepository(
        detailsRepositoryImpl : DetailsRepositoryImpl
    ) : DetailsRepository

}