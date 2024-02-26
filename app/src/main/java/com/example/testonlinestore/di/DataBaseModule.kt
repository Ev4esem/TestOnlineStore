package com.example.testonlinestore.di

import android.content.Context
import androidx.room.Room
import com.example.testonlinestore.data.database.dao.AccountDao
import com.example.testonlinestore.data.database.dao.CardDao
import com.example.testonlinestore.data.database.MainDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {

    @Provides
    @Singleton
    fun provideMainDataBase(@ApplicationContext context : Context) : MainDataBase =
        Room.databaseBuilder(
            context.applicationContext,
            MainDataBase::class.java,
            "main_database.db"
        )
            .build()

    @Provides
    @Singleton
    fun provideAccountDao(mainDataBase : MainDataBase) : AccountDao = mainDataBase.accountDao()

    @Provides
    @Singleton
    fun provideCardDao(mainDataBase : MainDataBase) : CardDao = mainDataBase.cardDao()

}