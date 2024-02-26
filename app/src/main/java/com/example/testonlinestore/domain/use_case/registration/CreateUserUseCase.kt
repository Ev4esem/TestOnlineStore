package com.example.testonlinestore.domain.use_case.registration

import com.example.testonlinestore.domain.model.registration.UserAccount
import com.example.testonlinestore.domain.repository.RegistrationRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CreateUserUseCase @Inject constructor (private val registrationRepository : RegistrationRepository) {

     suspend operator fun invoke(userAccount : UserAccount)  {

        return registrationRepository.createUser(userAccount)

    }

}