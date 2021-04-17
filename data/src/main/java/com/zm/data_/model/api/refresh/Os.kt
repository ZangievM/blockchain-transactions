package com.zm.data_.model.api.refresh


import com.google.gson.annotations.SerializedName

data class Os(
    @SerializedName("full_name")
    val fullName: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("version")
    val version: String?
)