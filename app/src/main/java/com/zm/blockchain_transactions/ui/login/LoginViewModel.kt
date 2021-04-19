package com.zm.blockchain_transactions.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zm.domain.model.SessionData
import com.zm.domain.usecase.LoginUseCase
import com.zm.domain.util.Resource
import com.zm.domain.util.Resource.*
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject


class LoginViewModel @Inject constructor(private val loginUseCase: LoginUseCase) : ViewModel() {
    private val loginState = MutableLiveData<Resource<SessionData>>()
    val loginResult: LiveData<Resource<SessionData>> = loginState

    fun login(username: String, password: String) {
        loginUseCase.execute(username, password)
            .onStart {
                loginState.postValue(Loading())
            }
            .onEach {
                loginState.postValue(Success(it))
            }
            .catch { error ->
                loginState.postValue(Failure(error))
            }.launchIn(viewModelScope)
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}