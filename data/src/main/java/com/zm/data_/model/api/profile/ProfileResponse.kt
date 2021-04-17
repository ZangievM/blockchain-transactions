package com.zm.data_.model.api.profile


import com.google.gson.annotations.SerializedName

data class ProfileResponse(
    @SerializedName("info")
    val info: Info
)