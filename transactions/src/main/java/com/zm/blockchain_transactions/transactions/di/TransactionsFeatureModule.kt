package com.zm.blockchain_transactions.transactions.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zm.blockchain_transactions.di.ViewModelFactory
import com.zm.blockchain_transactions.di.ViewModelKey
import com.zm.blockchain_transactions.transactions.TransactionsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class TransactionsFeatureModule {
    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(TransactionsViewModel::class)
    internal abstract fun profileViewModel(viewModel: TransactionsViewModel): ViewModel
}