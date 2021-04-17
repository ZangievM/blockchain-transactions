package com.zm.data_.model.api.profile


import com.google.gson.annotations.SerializedName

data class Info(
    @SerializedName("account")
    val account: Account,
    @SerializedName("profiles")
    val profiles: List<Profile>,
    @SerializedName("session")
    val session: Session
)