package com.example.seasalonapp.data.model.request

import com.google.gson.annotations.SerializedName

data class ReservationRequest(
    @SerializedName("user_id")
    val user_id: Int,
    @SerializedName("user_name")
    val user_name: String,
    @SerializedName("phone_number")
    val phone_number: String,
    @SerializedName("services")
    val services: String,
    @SerializedName("date")
    val date: String,
    @SerializedName("time_start")
    val time_start: String,
    @SerializedName("time_end")
    val time_end: String,
)
