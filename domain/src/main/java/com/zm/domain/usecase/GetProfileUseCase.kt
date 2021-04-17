package com.zm.domain.usecase

import com.zm.domain.model.Profile
import com.zm.domain.repository.ProfileRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetProfileUseCase @Inject constructor(
    private val profileRepository: ProfileRepository
) {
    fun execute(): Flow<Profile> {
        return flow {
            val profile = profileRepository.getProfile()
            emit(profile)
        }
    }
}