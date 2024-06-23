package com.example.seasalonapp.data.repository.reservation

import com.example.seasalonapp.data.model.request.RegisterRequest
import com.example.seasalonapp.data.model.request.ReservationRequest
import com.example.seasalonapp.data.remote.RetrofitClient

class ReservationRepository {

    private val api = RetrofitClient.instance
    suspend fun reservation(token: String, reservationRequest: ReservationRequest) {
        return api.reservation("Bearer $token", reservationRequest)
    }
}