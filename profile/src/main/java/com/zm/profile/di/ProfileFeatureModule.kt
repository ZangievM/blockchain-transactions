package com.zm.profile.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zm.blockchain_transactions.di.ViewModelFactory
import com.zm.blockchain_transactions.di.ViewModelKey
import com.zm.profile.ProfileViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ProfileFeatureModule {
    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel::class)
    internal abstract fun profileViewModel(viewModel: ProfileViewModel): ViewModel
}