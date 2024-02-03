package com.example.testonlinestore.presentation.use_case.profile

import com.example.testonlinestore.domain.model.registration.UserAccount
import com.example.testonlinestore.presentation.repository.ProfileRepository
import javax.inject.Inject

class RemoveAccountUseCase @Inject constructor(private val profileRepository : ProfileRepository) {

    suspend operator fun invoke(user : UserAccount) {
        return profileRepository.removeAccount(user)
    }

}