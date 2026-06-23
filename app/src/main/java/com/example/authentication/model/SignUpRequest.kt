package com.example.authentication.model

data class SignupRequest(
    val email: String,
    val username: String,
    val password: String,
    val name:String,
    val address: String,
    val phone:String
)

data class SignupResponse(
    val id: Int,
    val token: String?,
    val error: String?
)
