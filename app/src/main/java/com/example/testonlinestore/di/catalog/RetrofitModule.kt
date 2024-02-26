package com.example.testonlinestore.di.catalog

import com.example.testonlinestore.data.database.dao.CardDao
import com.example.testonlinestore.data.repositories.CatalogRepositoryImpl
import com.example.testonlinestore.data.retrofit.CatalogAPI
import com.example.testonlinestore.di.IoDispatcher
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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
        catalogAPI : CatalogAPI,
        cardDao : CardDao,
        @IoDispatcher dispatcher : CoroutineDispatcher
    ) : CatalogRepositoryImpl {
        return CatalogRepositoryImpl(
            catalogAPI,
            cardDao,
            dispatcher
        )
    }


}