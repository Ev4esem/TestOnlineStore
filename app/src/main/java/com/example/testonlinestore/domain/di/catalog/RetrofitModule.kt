package com.example.testonlinestore.domain.di.catalog

import com.example.testonlinestore.domain.repositories.CatalogRepositoryImpl
import com.example.testonlinestore.domain.retrofit.CatalogAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton



@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    private const val BASE_URL = "https://run.mocky.io/v3/"

    @Provides
    @Singleton
    fun provideRetrofit() : Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    @Provides
    @Singleton
    fun provideMainService(retrofit : Retrofit) : CatalogAPI = retrofit.create(CatalogAPI::class.java)

    @Provides
    @Singleton
    fun provideCatalogRepositoryImpl(
        catalogAPI : CatalogAPI
    ) : CatalogRepositoryImpl = CatalogRepositoryImpl(catalogAPI)

}