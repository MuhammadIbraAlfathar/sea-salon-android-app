package com.example.seasalonapp.data.model.request

import com.google.gson.annotations.SerializedName

data class RegisterRequest(
    @SerializedName("name")
    val name: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("phone_number")
    val phone_number: String,
    @SerializedName("password")
    val password: String,
)
