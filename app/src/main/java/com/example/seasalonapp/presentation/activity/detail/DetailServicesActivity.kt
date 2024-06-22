package com.example.seasalonapp.presentation.activity.detail

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.example.seasalonapp.data.model.response.mainservice.Services
import com.example.seasalonapp.databinding.ActivityDetailServicesBinding
import com.example.seasalonapp.presentation.activity.reservation.ReservationActivity


class DetailServicesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailServicesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetailServicesBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val id = intent.getStringExtra("SERVICE_ID")
        val name = intent.getStringExtra("SERVICE_NAME")
        val img = intent.getStringExtra("SERVICE_IMG")
        val desc = intent.getStringExtra("SERVICE_DESCRIPTION")
        val duration = intent.getStringExtra("SERVICE_DURATION")


        val dataServices: Services? = intent.getParcelableExtra("dataService")


        dataServices.let {

            Glide.with(this)
                .load(it?.picturePath)
                .into(binding.ivDetailService)

            binding.tvNameService.text = it?.services_name
            binding.tvDescription.text = it?.description
            binding.duration.text = it?.duration.toString()

        }

        binding.btnReservation.setOnClickListener {
            val reservation = Intent(this, ReservationActivity::class.java).apply {
                putExtra("dataService", dataServices)
            }
            startActivity(reservation)
        }



    }
}