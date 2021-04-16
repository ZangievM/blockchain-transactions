package com.zm.domain.repository

import com.zm.domain.model.SessionData

interface SessionRepository {
    suspend fun saveSession(session: SessionData)
    suspend fun clearSession()
}