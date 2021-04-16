package com.zm.domain.repository

import com.zm.domain.model.Profile

interface ProfileRepository {
    suspend fun getProfile(): Profile
    suspend fun saveProfileInfo(profile: Profile)
    suspend fun clearProfileInfo()
}