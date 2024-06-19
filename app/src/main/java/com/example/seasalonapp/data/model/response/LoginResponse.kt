package com.example.seasalonapp.data.model.response



data class LoginResponse(
    val meta: Meta,
    val data: Data
)

data class Meta(
    val code: Int,
    val status: String,
    val message: String
)

data class Data(
    val access_token: String,
    val token_type: String,
    val user: User
)

data class User(
    val id: Int,
    val name: String,
    val email: String,
    val phone_number: String,
    val roles: String,
    val email_verified_at: String?,
    val two_factor_confirmed_at: String?,
    val current_team_id: String?,
    val profile_photo_path: String?,
    val created_at: String,
    val updated_at: String,
    val profile_photo_url: String
)
