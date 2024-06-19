package com.example.seasalonapp.data.remote

import com.example.seasalonapp.data.model.request.LoginRequest
import com.example.seasalonapp.data.model.response.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {
//    @FormUrlEncoded
    @POST("api/login")
    suspend fun login(
        @Body loginRequest: LoginRequest
    ): Response<LoginResponse>
}