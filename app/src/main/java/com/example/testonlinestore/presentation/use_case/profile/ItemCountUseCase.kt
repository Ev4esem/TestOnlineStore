package com.example.testonlinestore.presentation.use_case.profile

import com.example.testonlinestore.presentation.repository.ProfileRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ItemCountUseCase @Inject constructor(private val profileRepository : ProfileRepository) {

    suspend operator fun invoke() : Int {
        return profileRepository.getItemCountFlow()
    }

}