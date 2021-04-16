package com.zm.domain.repository

import com.zm.domain.model.SessionData
import com.zm.domain.model.User
import com.zm.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun login(username: String, password: String): SessionData
    suspend fun logout()
}