package com.example.seasalonapp.data.repository.review

import com.example.seasalonapp.data.model.request.ReviewRequest
import com.example.seasalonapp.data.model.response.review.ReviewResponse
import com.example.seasalonapp.data.remote.RetrofitClient
import retrofit2.Retrofit

class ReviewRepository {

    private val api = RetrofitClient.instance
    suspend fun submitReview(token: String, reviewRequest: ReviewRequest) {
        return api.submitReview("Bearer $token", reviewRequest)
    }

    suspend fun getReviews(token: String): ReviewResponse {
        return api.getReviews("Bearer $token")
    }
}