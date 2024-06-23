package com.example.seasalonapp.data.model.response.reservation

data class HistoryReservationResponse(
    val meta: Meta,
    val data: ReservationData
)

data class Meta(
    val code: Int,
    val status: String,
    val message: String
)

data class ReservationData(
    val data_reservation: List<Reservation>
)

data class Reservation(
    val id: Int,
    val user_id: Int,
    val user_name: String,
    val phone_number: String,
    val services: String,
    val date: String,
    val time_start: String,
    val time_end: String,
    val created_at: String,
    val updated_at: String
)
