package com.example.seasalonapp.data.model.response.mainservice

data class Services(
    val id: Int,
    val services_name: String,
    val duration: Int,
    val description: String,
    val picturePath: String,
    val created_at: String,
    val updated_at: String
)