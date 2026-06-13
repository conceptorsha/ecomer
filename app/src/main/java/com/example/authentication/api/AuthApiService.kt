package com.example.authentication.api

import retrofit2.http.GET
import retrofit2.http.POST

//retrofit
interface AuthApiService {

    @POST("auth/me")
    suspend fun postCurrentUser(): UserResponse
}