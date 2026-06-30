package com.example.authentication.Repository

import com.example.authentication.model.LoginRequest
import com.example.authentication.model.LoginResponse
import com.example.authentication.model.ProductsResponse
import com.example.authentication.model.SignupRequest
import com.example.authentication.model.SignupResponse
import com.example.authentication.remote.RetrofitInstance
import retrofit2.Response

class AuthRepository {

    private val api = RetrofitInstance.api

    suspend fun login(username: String, password: String): Response<LoginResponse> {
        return api.login(LoginRequest(username, password))
    }

    suspend fun signup(email: String,username: String, password: String,name: String,address: String,phone: String): Response<SignupResponse> {
        return api.signup(SignupRequest(email,username, password,name,address,phone))
    }
    suspend fun getProducts(): Response<List<ProductsResponse>> {
        return api.getProducts()
    }
}