package com.zm.data_.model.api.refresh


import com.google.gson.annotations.SerializedName

data class Browser(
    @SerializedName("bot")
    val bot: Boolean?,
    @SerializedName("browser")
    val browser: String?,
    @SerializedName("browser_version")
    val browserVersion: String?,
    @SerializedName("engine")
    val engine: String?,
    @SerializedName("engine_version")
    val engineVersion: String?,
    @SerializedName("mobile")
    val mobile: Boolean?,
    @SerializedName("os")
    val os: Os?,
    @SerializedName("platform")
    val platform: String?
)