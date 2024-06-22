package com.example.seasalonapp.presentation.activity.reservation

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.TimePicker
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.example.seasalonapp.R
import com.example.seasalonapp.data.model.response.mainservice.Services
import com.example.seasalonapp.databinding.ActivityLoginBinding
import com.example.seasalonapp.databinding.ActivityReservationBinding
import java.util.Calendar

class ReservationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityReservationBinding
    private var dataService: Services? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityReservationBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        dataService = intent.getParcelableExtra("dataService")

        dataService.let {
            Glide.with(this)
                .load(it?.picturePath)
                .into(binding.ivServices)

            binding.tvServiceName.text = it?.services_name
            binding.tvDuration.text = it?.duration.toString()
        }

//        val branchNames = arrayOf("Branch 1", "Branch 2", "Branch 3", "Branch 4")
//        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, branchNames)
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//
//        binding.spinnerBranch.adapter = adapter

        binding.btnDatePicker.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(
                this,
                { _, selectedYear, selectedMonth, selectedDay ->
                    val dateString = "$selectedYear-${selectedMonth + 1}-$selectedDay"
                    binding.tvSelectedDate.text = dateString
                    binding.tvSelectedDate.visibility = View.VISIBLE
                },
                year,
                month,
                day
            )

            datePickerDialog.show()
        }

        binding.btnTimePicker.setOnClickListener {
            val timePicker = TimePickerDialog(this, timePickerListener, 12, 0, false)
            timePicker.show()
        }
        binding.btnTimePickerEnd.setOnClickListener {
            val timePicker = TimePickerDialog(this, timePickerEndListener, 12, 0, false)
            timePicker.show()
        }

    }

    private val timePickerEndListener =
        TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
            binding.tvSelectedTimeEnd.text = String.format("%02d:%02d", hourOfDay, minute)
            binding.tvSelectedTimeEnd.visibility = View.VISIBLE // Make the selected time visible
        }

    private val timePickerListener =
        TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
            binding.tvSelectedTimeStart.text = String.format("%02d:%02d", hourOfDay, minute)
            binding.tvSelectedTimeStart.visibility = View.VISIBLE // Make the selected time visible
        }
}