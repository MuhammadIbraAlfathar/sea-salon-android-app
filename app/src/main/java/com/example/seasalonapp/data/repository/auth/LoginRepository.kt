package com.example.seasalonapp.data.repository.auth

import com.example.seasalonapp.data.model.request.LoginRequest
import com.example.seasalonapp.data.model.response.LoginResponse
import com.example.seasalonapp.data.remote.RetrofitClient

class LoginRepository {
    private val api = RetrofitClient.instance

    suspend fun login(loginRequest: LoginRequest): retrofit2.Response<LoginResponse> {
        return api.login(loginRequest)
    }
}