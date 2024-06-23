@file:Suppress("DEPRECATION")

package com.example.seasalonapp.presentation.activity.reservation

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.seasalonapp.data.model.request.ReservationRequest
import com.example.seasalonapp.data.model.response.mainservice.Services
import com.example.seasalonapp.data.repository.reservation.ReservationRepository
import com.example.seasalonapp.databinding.ActivityReservationBinding
import com.example.seasalonapp.helper.PreferenceHelper
import com.example.seasalonapp.presentation.viewmodel.reservation.ReservationViewModel
import com.example.seasalonapp.presentation.viewmodel.reservation.ReservationViewModelFactory
import java.util.Calendar

@SuppressLint("DefaultLocale")
class ReservationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityReservationBinding
    private var dataService: Services? = null
    private lateinit var viewModel: ReservationViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityReservationBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        val repository = ReservationRepository()
        val factory = ReservationViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory)[ReservationViewModel::class.java]

        val token = PreferenceHelper.getAccessToken(this).toString()

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

        val userId = PreferenceHelper.getUser(this)?.id


        binding.btnSubmitReservation.setOnClickListener {
            val userName = binding.inputName.text.toString()
            val phoneNumber = binding.inputName.text.toString()
            val services = dataService?.services_name.toString()
            val date = binding.tvSelectedDate.text.toString()
            val timeStart = binding.tvSelectedTimeStart.text.toString()
            val timeEnd = binding.tvSelectedTimeEnd.text.toString()


            val reservation = ReservationRequest(
                user_id = userId!!,
                user_name = userName,
                phone_number = phoneNumber,
                services = services,
                date = date,
                time_start = timeStart,
                time_end = timeEnd
            )

            viewModel.reservation(token, reservation)

            val intent = Intent(this, SuccessReservationActivity::class.java)
            startActivity(intent)


        }


        viewModel.errorMessage.observe(this) {message ->
            message?.let {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
                Log.d("ERROR_REGISTER", it)
            }
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