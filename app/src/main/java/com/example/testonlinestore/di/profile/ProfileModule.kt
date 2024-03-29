package com.example.testonlinestore.di.profile

import com.example.testonlinestore.data.repositories.ProfileRepositoryImpl
import com.example.testonlinestore.domain.repository.ProfileRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface ProfileModule {

    @Binds
    fun profileRepositoryImpl_to_profileRepository(
        profileRepositoryImpl : ProfileRepositoryImpl
    ) : ProfileRepository

}