package com.zm.domain.model


data class SessionData(
    val expiration: Long,
    val serverTime: Long,
    val token: String
)