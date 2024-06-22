package com.example.seasalonapp.data.repository.auth

import com.example.seasalonapp.data.model.request.LoginRequest
import com.example.seasalonapp.data.model.request.RegisterRequest
import com.example.seasalonapp.data.model.response.LoginResponse
import com.example.seasalonapp.data.remote.RetrofitClient

class RegisterRepository {
    private val api = RetrofitClient.instance

    suspend fun register(registerRequest: RegisterRequest) {
        return api.register(registerRequest)
    }
}