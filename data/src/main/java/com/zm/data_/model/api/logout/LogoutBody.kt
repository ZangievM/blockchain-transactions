package com.zm.data_.model.api.logout


import com.google.gson.annotations.SerializedName

data class LogoutBody(
    @SerializedName("session_id")
    val sessionId: String
)