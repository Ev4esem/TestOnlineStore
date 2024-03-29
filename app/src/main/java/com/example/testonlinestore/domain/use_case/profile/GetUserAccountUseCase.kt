package com.example.testonlinestore.domain.use_case.profile

import com.example.testonlinestore.domain.model.registration.UserAccount
import com.example.testonlinestore.domain.repository.ProfileRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserAccountUseCase @Inject constructor(private val profileRepository : ProfileRepository) {

    operator fun invoke(number : String) : Flow<UserAccount?> {
        return profileRepository.getDataAccount(number.toLong())
    }

}