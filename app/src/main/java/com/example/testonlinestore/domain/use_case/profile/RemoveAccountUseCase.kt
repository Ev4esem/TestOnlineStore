package com.example.testonlinestore.domain.use_case.profile

import com.example.testonlinestore.domain.repository.ProfileRepository
import javax.inject.Inject

class RemoveAccountUseCase @Inject constructor(private val profileRepository : ProfileRepository) {

     suspend operator fun invoke(user : String) : Int  {
        return profileRepository.removeAccount(user.toLong())
    }

}