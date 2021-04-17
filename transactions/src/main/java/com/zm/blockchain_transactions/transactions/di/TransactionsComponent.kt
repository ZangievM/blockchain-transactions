package com.zm.blockchain_transactions.transactions.di

import android.content.Context
import com.zm.blockchain_transactions.di.CoreDependencies
import com.zm.blockchain_transactions.transactions.TransactionsFragment
import dagger.BindsInstance
import dagger.Component

@Component(
    dependencies = [
        CoreDependencies::class
    ],
    modules = [
        TransactionsFeatureModule::class
    ]
)
interface TransactionsComponent {
    fun inject(fragment: TransactionsFragment)

    @Component.Builder
    interface Builder {
        fun context(@BindsInstance context: Context): Builder
        fun dependencies(dependencies: CoreDependencies): Builder
        fun build(): TransactionsComponent
    }
}