package com.example.seasalonapp.data.model.response.review

data class ReviewResponse(
    val meta: Meta,
    val data: ReviewData
)

data class Meta(
    val code: Int,
    val status: String,
    val message: String
)

data class ReviewData(
    val reviews: List<Review>
)

data class Review(
    val id: Int,
    val user_id: Int,
    val name: String,
    val rating: Int,
    val comment: String,
    val created_at: String,
    val updated_at: String
)
