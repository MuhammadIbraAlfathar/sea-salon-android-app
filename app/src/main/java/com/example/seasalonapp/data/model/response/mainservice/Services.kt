package com.example.seasalonapp.data.model.response.mainservice

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Services(
    val id: Int,
    val services_name: String,
    val duration: Int,
    val description: String,
    val picturePath: String,
    val created_at: String,
    val updated_at: String
): Parcelable