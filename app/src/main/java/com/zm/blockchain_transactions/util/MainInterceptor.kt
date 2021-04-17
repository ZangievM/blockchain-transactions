package com.zm.blockchain_transactions.util

import android.content.SharedPreferences
import com.zm.data_.api.MainRefreshApi
import com.zm.data_.mappers.toSessionData
import com.zm.domain.repository.SessionRepository
import kotlinx.coroutines.runBlocking
import okhttp3.*
import javax.inject.Inject

class MainInterceptor @Inject constructor(
    private val sessionRepository: SessionRepository,
    private val refreshApi: MainRefreshApi
): Interceptor, Authenticator {
    private val monitor = Any()
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        runBlocking {
            if (sessionRepository.isLoggedIn()) {
                request = request.newBuilder()
                    .addHeader("Authorization", sessionRepository.getSession().token)
                    .build()
            }
        }
        return chain.proceed(request)
    }

    override fun authenticate(route: Route?, response: Response): Request {
        synchronized(monitor) {
            return runBlocking {
                val request = response.request
                val token = sessionRepository.getSession().token
                // check if token was refreshed already
                if (response.request.header("Authorization") != token) {
                    request.newBuilder().addHeader("Authorization", token).build()
                }
                // if current token is the same we need to refresh it
                else {
                    val newSession = refreshApi.refresh().toSessionData()
                    sessionRepository.saveSession(newSession)
                    request.newBuilder().addHeader("Authorization", newSession.token).build()
                }
            }
        }
    }
}