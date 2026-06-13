package com.example.authentication.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance{
    private const val BASE_URL = "https://dummyjson.com/"
    val  api: AuthApiService by  lazy{
        Retrofit.Builder()
        .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(AuthApiService::class.java)
    }
}