package com.zm.data_.model.api.refresh


import com.google.gson.annotations.SerializedName

data class Session(
    @SerializedName("account_id")
    val accountId: String?,
    @SerializedName("browser")
    val browser: Browser?,
    @SerializedName("client_agent")
    val clientAgent: String?,
    @SerializedName("client_city")
    val clientCity: String?,
    @SerializedName("client_country")
    val clientCountry: String?,
    @SerializedName("client_country_iso")
    val clientCountryIso: String?,
    @SerializedName("client_ip")
    val clientIp: String?,
    @SerializedName("created_at")
    val createdAt: String?,
    @SerializedName("device")
    val device: Any?,
    @SerializedName("expiry_at")
    val expiryAt: String?,
    @SerializedName("last_use")
    val lastUse: String?,
    @SerializedName("session_id")
    val sessionId: String,
    @SerializedName("verifications")
    val verifications: List<String>?
)