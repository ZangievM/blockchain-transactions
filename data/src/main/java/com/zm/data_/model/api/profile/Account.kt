package com.zm.data_.model.api.profile


import com.google.gson.annotations.SerializedName

data class Account(
    @SerializedName("account_id")
    val accountId: String,
    @SerializedName("account_type")
    val accountType: String?,
    @SerializedName("created_at")
    val createdAt: String?,
    @SerializedName("email")
    val email: String,
    @SerializedName("email_verified")
    val emailVerified: Boolean?,
    @SerializedName("2fa_method")
    val faMethod: String?,
    @SerializedName("password")
    val password: Any?,
    @SerializedName("phone")
    val phone: String?,
    @SerializedName("totp_verified")
    val totpVerified: Boolean?
)