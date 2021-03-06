package com.zm.blockchain_transactions.profile.di

import android.content.Context
import com.zm.blockchain_transactions.profile.ProfileFragment
import com.zm.blockchain_transactions.di.CoreDependencies
import dagger.BindsInstance
import dagger.Component

@Component(
    dependencies = [CoreDependencies::class],
    modules = [ProfileFeatureModule::class]
    )
interface ProfileComponent {
    fun inject(profileFragment: ProfileFragment)

    @Component.Builder
    interface Builder {
        fun context(@BindsInstance context: Context): Builder
        fun dependencies(dependencies: CoreDependencies): Builder
        fun build(): ProfileComponent
    }
}