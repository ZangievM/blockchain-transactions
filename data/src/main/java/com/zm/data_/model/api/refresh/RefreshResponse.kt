package com.zm.data_.model.api.refresh


import com.google.gson.annotations.SerializedName

data class RefreshResponse(
    @SerializedName("expiration")
    val expiration: String,
    @SerializedName("server_time")
    val serverTime: String,
    @SerializedName("session")
    val session: Session,
    @SerializedName("token")
    val token: String
)