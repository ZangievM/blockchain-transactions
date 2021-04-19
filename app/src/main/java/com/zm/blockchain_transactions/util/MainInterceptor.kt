package com.zm.blockchain_transactions.util

import android.content.SharedPreferences
import com.zm.data_.api.MainRefreshApi
import com.zm.data_.mappers.toSessionData
import com.zm.domain.model.SessionData
import com.zm.domain.repository.SessionRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
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
                    request.newBuilder().removeHeader("Authorization").addHeader("Authorization", token).build()
                }
                // if current token is the same we need to refresh it
                else {
                    val newSession:SessionData =
                    flow { emit(refreshApi.refresh().toSessionData()) }.catch {
                        emit(SessionData(0L,""))
                    }.first()
                    if (newSession.token.isNotBlank()) {
                        sessionRepository.saveSession(newSession)
                        request.newBuilder().removeHeader("Authorization").addHeader("Authorization", newSession.token).build()
                    } else request
                }
            }
        }
    }
}