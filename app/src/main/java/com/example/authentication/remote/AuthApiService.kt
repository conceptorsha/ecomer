package com.example.authentication.remote

import com.example.authentication.model.LoginRequest
import com.example.authentication.model.LoginResponse
import com.example.authentication.model.ProductsResponse
import com.example.authentication.model.SignupRequest
import com.example.authentication.model.SignupResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

//retrofit
interface authApiService {

    @POST("auth/login")
    suspend fun login(
        @Body request: LoginRequest
    ): Response<LoginResponse>

    @POST("users")
    suspend fun signup(
        @Body request: SignupRequest
    ): Response<SignupResponse>
    @GET("products")
    suspend fun getProducts(): Response<List<ProductsResponse>>
}