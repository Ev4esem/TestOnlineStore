package com.example.testonlinestore.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.example.testonlinestore.utils.dataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ViewModelComponent::class)
object DataStoreModule {

    @Provides
    fun provideDataStore(@ApplicationContext context : Context) : DataStore<Preferences> {
        return context.dataStore
    }

}