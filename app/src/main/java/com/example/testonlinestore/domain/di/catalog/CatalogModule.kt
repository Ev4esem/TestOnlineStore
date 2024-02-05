package com.example.testonlinestore.domain.di.catalog

import com.example.testonlinestore.domain.repositories.CatalogRepositoryImpl
import com.example.testonlinestore.presentation.repository.CatalogRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface CatalogModule {

    @Binds
    fun catalogRepositoryImpl_to_catalogRepository(
        catalogRepositoryImpl : CatalogRepositoryImpl
    ) : CatalogRepository

}