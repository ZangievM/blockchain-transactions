package com.zm.blockchain_transactions.di

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.google.gson.Gson
import com.zm.blockchain_transactions.BuildConfig
import com.zm.blockchain_transactions.util.MainInterceptor
import com.zm.data_.api.MainApi
import com.zm.data_.api.MainRefreshApi
import com.zm.data_.repository.AuthRepositoryImpl
import com.zm.data_.repository.ProfileRepositoryImpl
import com.zm.data_.repository.SessionRepositoryImpl
import com.zm.domain.repository.AuthRepository
import com.zm.domain.repository.ProfileRepository
import com.zm.domain.repository.SessionRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module(
    includes = [
        DataModule::class
    ]
)
object AppModule {

    @Singleton
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Reusable
    @Provides
    fun provideGson(): Gson {
        return Gson()
    }

    @Reusable
    @Provides
    fun provideGsonConverterFactory(gson: Gson): Converter.Factory {
        return GsonConverterFactory.create(gson)
    }

    @Singleton
    @Provides
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        val masterKey = MasterKey.Builder(context, MasterKey.DEFAULT_MASTER_KEY_ALIAS)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()
        return EncryptedSharedPreferences.create(
            context,
            "app_shared_prefs",
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        );
    }

    @Singleton
    @Provides
    fun provideMainInterceptor(
        refreshApi: MainRefreshApi,
        sessionRepository: SessionRepository
    ): Interceptor {

        return MainInterceptor(sessionRepository, refreshApi)
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(
        mainInterceptor: Interceptor,
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder().apply {
            addInterceptor(mainInterceptor)
            if (BuildConfig.DEBUG) addInterceptor(loggingInterceptor)
        }.build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(
        client: OkHttpClient,
        converterFactory: Converter.Factory
    ): Retrofit {
        return Retrofit
            .Builder()
            .client(client)
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(converterFactory)
            .build()
    }

    @Singleton
    @Provides
    fun provideApi(retrofit: Retrofit): MainApi {
        return retrofit.create(MainApi::class.java)
    }

    @Singleton
    @Provides
    fun provideRefreshApi(
        loggingInterceptor: HttpLoggingInterceptor,
        converterFactory: Converter.Factory,
    ): MainRefreshApi {
        val client = OkHttpClient.Builder().apply {
            if (BuildConfig.DEBUG) addInterceptor(loggingInterceptor)
        }.build()
        val retrofit = Retrofit
            .Builder()
            .client(client)
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(converterFactory)
            .build()
        return retrofit.create(MainRefreshApi::class.java)
    }
}

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {
    @Binds
    @Singleton
    fun bindAuthRepository(repository: AuthRepositoryImpl): AuthRepository

    @Binds
    @Singleton
    fun bindSessionRepository(repository: SessionRepositoryImpl): SessionRepository

    @Binds
    @Singleton
    fun bindProfileRepository(repository: ProfileRepositoryImpl): ProfileRepository
}