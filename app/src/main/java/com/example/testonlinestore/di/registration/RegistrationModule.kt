package com.example.testonlinestore.di.registration

import com.example.testonlinestore.data.repositories.RegistrationRepositoryImpl
import com.example.testonlinestore.domain.repository.RegistrationRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
interface RegistrationModule {

    @Binds
    fun repositoryRegistrationImpl_to_repositoryRegistration(
        registrationRepositoryImpl : RegistrationRepositoryImpl
    ) : RegistrationRepository


}