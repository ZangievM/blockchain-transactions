package com.zm.domain.model

data class Profile(
    val email: String,
    val accountId: String,
    val firstName: String?,
    val lastName: String?,
    val avatarUrl: String?
)