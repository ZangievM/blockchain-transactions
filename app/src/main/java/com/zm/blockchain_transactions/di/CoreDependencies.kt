package com.zm.blockchain_transactions.di

import com.zm.domain.usecase.GetProfileUseCase
import com.zm.domain.usecase.LoginUseCase
import com.zm.domain.usecase.LogoutUseCase
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface CoreDependencies {
    fun loginUseCase(): LoginUseCase
    fun getProfileUseCase(): GetProfileUseCase
    fun logoutUseCase(): LogoutUseCase
}