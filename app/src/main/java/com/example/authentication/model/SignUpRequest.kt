package com.example.authentication.model

data class SignupRequest(
    val email: String,
    val password: String
)

data class SignupResponse(
    val id: Int,
    val token: String?,
    val error: String?
)
