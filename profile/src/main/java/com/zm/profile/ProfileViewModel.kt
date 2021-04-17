package com.zm.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zm.domain.model.Profile
import com.zm.domain.usecase.GetProfileUseCase
import com.zm.domain.usecase.LogoutUseCase
import com.zm.domain.util.Resource
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
    private val getProfileUseCase: GetProfileUseCase,
    private val logoutUseCase: LogoutUseCase
) : ViewModel() {

    private val profileLiveData = MutableLiveData<Resource<Profile>>().apply {
        getProfileData()
    }
    val profile: LiveData<Resource<Profile>> = profileLiveData

    private val logoutLiveData = MutableLiveData<Resource<Unit>>()
    val logout: LiveData<Resource<Unit>> = logoutLiveData

    private fun getProfileData() {
        getProfileUseCase.execute()
            .onStart {
                profileLiveData.postValue(Resource.Loading())
            }
            .onEach { profile ->
                profileLiveData.postValue(Resource.Success(profile))
            }
            .catch { error ->
                profileLiveData.postValue(Resource.Failure(error))
            }.launchIn(viewModelScope)
    }

    fun logout() {
        logoutUseCase.execute()
            .onStart {
                logoutLiveData.postValue(Resource.Loading())
            }
            .onEach { profile ->
                logoutLiveData.postValue(Resource.Success(Unit))
            }
            .catch { error ->
                profileLiveData.postValue(Resource.Failure(error))
            }.launchIn(viewModelScope)
    }
}