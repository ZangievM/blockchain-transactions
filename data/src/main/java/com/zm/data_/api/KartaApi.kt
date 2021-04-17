package com.zm.data_.api

import com.zm.data_.model.api.login.LoginBody
import com.zm.data_.model.api.login.LoginResponse
import com.zm.data_.model.api.logout.LogoutBody
import com.zm.data_.model.api.profile.ProfileResponse
import com.zm.data_.model.api.refresh.RefreshResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface KartaApi {

    @POST("/accounts/auth")
    suspend fun login(@Body loginBody: LoginBody): LoginResponse

    @POST("/accounts/sessions/end")
    suspend fun logout(@Body logoutBody: LogoutBody = LogoutBody("bcd4f3f6-a302-45ec-a17c-69f09c72eb0d")): Any


    @POST("/accounts/sessions/refresh")
    suspend fun refresh(): RefreshResponse


    @GET("/accounts/current")
    suspend fun getProfile(): ProfileResponse


}