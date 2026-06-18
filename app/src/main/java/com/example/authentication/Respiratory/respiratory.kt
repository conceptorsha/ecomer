package com.example.authentication.Respiratory

import com.example.authentication.model.LoginRequest
import com.example.authentication.model.LoginResponse
import com.example.authentication.model.SignupRequest
import com.example.authentication.model.SignupResponse
import com.example.authentication.remote.RetrofitInstance

class AuthRepository {

    private val api = RetrofitInstance.api

    suspend fun login(email: String    ,password: String): LoginResponse {

        return     api.login(LoginRequest(email,password))

    }
    suspend fun  signup(email: String,password: String)   : SignupResponse{
        return  api.signup(SignupRequest(email,password))
    }
}