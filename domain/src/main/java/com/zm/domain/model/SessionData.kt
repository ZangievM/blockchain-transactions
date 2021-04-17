package com.zm.domain.model


data class SessionData(
    val expiration: Long,
    val token: String
)