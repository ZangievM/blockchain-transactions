package com.zm.data_.model.api.login


import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("expiration")
    val expiration: String,
    @SerializedName("server_time")
    val serverTime: String,
    @SerializedName("token")
    val token: String
)