package com.example.testonlinestore.domain.di.registration

import com.example.testonlinestore.domain.repositories.RegistrationRepositoryImpl
import com.example.testonlinestore.presentation.repository.RegistrationRepository
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