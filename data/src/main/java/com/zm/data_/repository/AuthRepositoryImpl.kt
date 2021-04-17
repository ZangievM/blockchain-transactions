package com.zm.data_.repository

import com.zm.data_.api.MainApi
import com.zm.data_.mappers.toSessionData
import com.zm.data_.model.api.login.LoginBody
import com.zm.domain.model.SessionData
import com.zm.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val api: MainApi
): AuthRepository {
    override suspend fun login(username: String, password: String): SessionData {
        val body = LoginBody(username, password)
        return api.login(body).toSessionData()
    }

    override suspend fun logout() {
        api.logout()
    }
}