package com.example.authentication.remote

import com.example.authentication.model.LoginRequest
import com.example.authentication.model.LoginResponse
import com.example.authentication.model.SignupRequest
import com.example.authentication.model.SignupResponse
import retrofit2.http.Body
import retrofit2.http.POST

//retrofit
interface authApiService {

    @POST("login")
    suspend fun login(
        @Body request: LoginRequest
    ): LoginResponse

    @POST("register")
    suspend fun signup(
        @Body request: SignupRequest
    ): SignupResponse
}