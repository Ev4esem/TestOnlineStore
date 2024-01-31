package com.example.testonlinestore.presentation.use_case.registration

import com.example.testonlinestore.domain.model.registration.UserAccount
import com.example.testonlinestore.presentation.repository.RegistrationRepository
import javax.inject.Inject

class CreateUserUseCase @Inject constructor (private val registrationRepository : RegistrationRepository) {

    suspend operator fun invoke(userAccount : UserAccount) {

        return registrationRepository.createUser(userAccount)

    }

}