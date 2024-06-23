package com.example.seasalonapp.presentation.activity.reservation

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.seasalonapp.R
import com.example.seasalonapp.databinding.ActivityReservationBinding
import com.example.seasalonapp.databinding.ActivitySuccessReservationBinding
import com.example.seasalonapp.presentation.activity.main.MainActivity
import com.example.seasalonapp.presentation.activity.main.ui.history.HistoryFragment

class SuccessReservationActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySuccessReservationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySuccessReservationBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnHome.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}