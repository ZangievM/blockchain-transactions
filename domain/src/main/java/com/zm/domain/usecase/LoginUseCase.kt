package com.zm.domain.usecase

import com.zm.domain.model.SessionData
import com.zm.domain.repository.AuthRepository
import com.zm.domain.repository.SessionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    private val sessionRepository: SessionRepository
) {
    fun execute(username: String, password: String): Flow<SessionData> {
        return flow {
            val result = authRepository.login(username, password)
            sessionRepository.saveSession(result)
            emit(result)
        }
    }
}