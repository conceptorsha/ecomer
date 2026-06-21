package com.example.authentication.Respiratory

import com.example.authentication.model.LoginRequest
import com.example.authentication.model.LoginResponse
import com.example.authentication.model.SignupRequest
import com.example.authentication.model.SignupResponse
import com.example.authentication.remote.RetrofitInstance
import retrofit2.Response

class AuthRepository {

    private val api = RetrofitInstance.api

    suspend fun login(email: String, password: String): Response<LoginResponse> {
        return api.login(LoginRequest(email, password))
    }

    suspend fun signup(email: String, password: String): Response<SignupResponse> {
        return api.signup(SignupRequest(email, password))
    }
}