package com.zm.domain.usecase

import com.zm.domain.repository.AuthRepository
import com.zm.domain.repository.ProfileRepository
import com.zm.domain.repository.SessionRepository
import com.zm.domain.util.Failure
import com.zm.domain.util.Loading
import com.zm.domain.util.Resource
import com.zm.domain.util.Success
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LogoutUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    private val profileRepository: ProfileRepository,
    private val sessionRepository: SessionRepository
) {
    fun execute(): Flow<Resource> {
        return flow {
            emit(Loading<Unit>())
            profileRepository.clearProfileInfo()
            sessionRepository.clearSession()
            authRepository.logout()
            emit(Success<Unit>())
        }.catch { error ->
            emit(Failure(error))
        }
    }
}