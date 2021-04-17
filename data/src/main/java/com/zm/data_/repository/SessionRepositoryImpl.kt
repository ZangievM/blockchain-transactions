package com.zm.data_.repository

import android.annotation.SuppressLint
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import com.zm.domain.model.SessionData
import com.zm.domain.repository.SessionRepository
import javax.inject.Inject

@SuppressLint("ApplySharedPref")
class SessionRepositoryImpl @Inject constructor(
    private val storage: SharedPreferences
) : SessionRepository {
    @Synchronized
    override suspend fun saveSession(session: SessionData) {
        storage.edit()
            .putString("token", session.token)
            .putLong("expiration", session.expiration)
            .commit()
    }

    @Synchronized
    override suspend fun getSession(): SessionData {
        val token = storage.getString("token", "")!!
        val expiration = storage.getLong("expiration", 0L)
        return SessionData(token = token, expiration = expiration)
    }

    @Synchronized
    override suspend fun clearSession() {
        storage.edit()
            .remove("token")
            .remove("expiration")
            .commit()
    }

    @Synchronized
    override suspend fun isLoggedIn(): Boolean {
        if (storage.getString("token", "").isNullOrBlank()) return false
        return true
    }
}