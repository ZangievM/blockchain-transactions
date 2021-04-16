package com.zm.domain.usecase

import com.zm.domain.repository.ProfileRepository
import com.zm.domain.util.Failure
import com.zm.domain.util.Loading
import com.zm.domain.util.Resource
import com.zm.domain.util.Success
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetProfileUseCase @Inject constructor(
    private val profileRepository: ProfileRepository
) {
    fun execute(): Flow<Resource> {
        return flow {
            emit(Loading<Unit>())
            val profile = profileRepository.getProfile()
            profileRepository.saveProfileInfo(profile)
            emit(Success(profile))
        }.catch { error ->
            emit(Failure(error))
        }
    }
}