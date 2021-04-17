package com.zm.domain.usecase

import com.zm.domain.repository.AuthRepository
import com.zm.domain.repository.ProfileRepository
import com.zm.domain.repository.SessionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LogoutUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    private val profileRepository: ProfileRepository,
    private val sessionRepository: SessionRepository
) {
    fun execute(): Flow<Unit> {
        return flow {
            profileRepository.clearProfileInfo()
            sessionRepository.clearSession()
            authRepository.logout()
            emit(Unit)
        }
    }
}