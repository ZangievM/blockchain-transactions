package com.zm.domain.usecase

import com.zm.domain.model.SessionData
import com.zm.domain.repository.AuthRepository
import com.zm.domain.util.Failure
import com.zm.domain.util.Loading
import com.zm.domain.util.Resource
import com.zm.domain.util.Success
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    fun execute(username: String, password: String): Flow<Resource> {
        return flow {
            emit(Loading<Unit>())
            val result = authRepository.login(username, password)
            emit(Success(result))
        }.catch { error ->
            emit(Failure(error))
        }
    }
}