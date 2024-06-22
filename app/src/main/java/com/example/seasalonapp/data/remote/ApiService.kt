package com.example.seasalonapp.data.remote

import MainServiceResponse
import com.example.seasalonapp.data.model.request.LoginRequest
import com.example.seasalonapp.data.model.request.ReviewRequest
import com.example.seasalonapp.data.model.response.LoginResponse
import com.example.seasalonapp.data.model.response.branchsalon.BranchSalonResponse
import com.example.seasalonapp.data.model.response.review.ReviewResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {
//    @FormUrlEncoded
    @POST("api/login")
    suspend fun login(
        @Body loginRequest: LoginRequest
    ): Response<LoginResponse>

    @GET("api/main-services")
    suspend fun getMainServices(): Response<MainServiceResponse>

    @POST("api/review")
    suspend fun submitReview(
        @Header("Authorization") token: String,
        @Body review: ReviewRequest
    )


    @GET("api/get-review")
    suspend fun getReviews(
        @Header("Authorization") token: String,
    ): ReviewResponse

    @GET("api/branch-salon")
    suspend fun getBranchSalon():BranchSalonResponse
}