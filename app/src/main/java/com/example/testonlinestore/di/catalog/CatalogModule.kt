package com.example.testonlinestore.di.catalog

import com.example.testonlinestore.data.database.dao.CardDao
import com.example.testonlinestore.data.repositories.CatalogRepositoryImpl
import com.example.testonlinestore.data.retrofit.CatalogAPI
import com.example.testonlinestore.domain.repository.CatalogRepository
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