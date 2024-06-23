package com.example.seasalonapp.presentation.activity.reservation

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.seasalonapp.R
import com.example.seasalonapp.data.model.response.reservation.Reservation
import com.example.seasalonapp.databinding.ActivityDetailHistoryBinding
import com.example.seasalonapp.databinding.ActivityReservationBinding

class DetailHistoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailHistoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetailHistoryBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val dataReservation: Reservation? = intent.getParcelableExtra("data_reservation")
        dataReservation.let {
            binding.userName.text = it?.user_name
            binding.phoneNumber.text = it?.phone_number
            binding.servicesName.text = it?.services
            binding.timeStart.text = it?.time_start
            binding.timeEnd.text = it?.time_end
            binding.dateResv.text = it?.date
        }
    }
}