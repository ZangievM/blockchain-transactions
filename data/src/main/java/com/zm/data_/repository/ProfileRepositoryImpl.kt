package com.zm.data_.repository

import android.content.SharedPreferences
import com.zm.data_.api.MainApi
import com.zm.data_.mappers.toProfile
import com.zm.domain.model.Profile
import com.zm.domain.repository.ProfileRepository
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val api: MainApi,
    private val storage: SharedPreferences
) : ProfileRepository {


    override suspend fun getProfile(): Profile {
        val profile = getProfileFromStorage()
        profile?.let {
            return it
        }
        val p = api.getProfile().toProfile()
        saveProfileInfo(p)
        return p
    }

    override suspend fun saveProfileInfo(profile: Profile) {
        storage.edit()
            .putString("email", profile.email)
            .putString("accountId", profile.accountId)
            .putString("firstName", profile.firstName)
            .putString("lastName", profile.lastName)
            .putString("avatar", profile.avatarUrl)
            .apply()
    }

    override suspend fun clearProfileInfo() {
        storage.edit()
            .remove("email")
            .remove("accountId")
            .remove("firstName")
            .remove("lastName")
            .remove("avatar")
            .apply()
    }

    private suspend fun getProfileFromStorage(): Profile? {
        val email = storage.getString("email", null) ?: return null
        val accountId = storage.getString("accountId", "") ?: return null
        val fName = storage.getString("firstName", "")
        val lName = storage.getString("lastName", "")
        val avatar = storage.getString("avatar", "")
        return Profile(
            email,
            accountId,
            fName,
            lName,
            avatar
        )
    }


}