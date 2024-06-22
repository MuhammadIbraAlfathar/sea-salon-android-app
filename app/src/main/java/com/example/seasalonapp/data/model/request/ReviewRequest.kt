package com.example.seasalonapp.data.model.request

import com.google.gson.annotations.SerializedName

data class ReviewRequest(
    @SerializedName("user_id")
    val user_id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("rating")
    val rating: Int,
    @SerializedName("comment")
    val comment: String
)
