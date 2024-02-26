package com.example.testonlinestore.domain.use_case.profile

import com.example.testonlinestore.domain.repository.ProfileRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ItemCountUseCase @Inject constructor(private val profileRepository : ProfileRepository) {

    suspend operator fun invoke() : Int {
        return profileRepository.getItemCountFlow()
    }

}