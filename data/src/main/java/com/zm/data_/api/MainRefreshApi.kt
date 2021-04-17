package com.zm.data_.api

import com.zm.data_.model.api.refresh.RefreshResponse
import retrofit2.http.POST

interface MainRefreshApi {
    @POST("api/accounts/sessions/refresh")
    suspend fun refresh(): RefreshResponse
}