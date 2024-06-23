package com.example.seasalonapp.presentation.viewmodel.reservation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.seasalonapp.data.model.request.RegisterRequest
import com.example.seasalonapp.data.model.request.ReservationRequest
import com.example.seasalonapp.data.model.response.reservation.Reservation
import com.example.seasalonapp.data.repository.auth.RegisterRepository
import com.example.seasalonapp.data.repository.reservation.ReservationRepository
import com.example.seasalonapp.presentation.viewmodel.auth.RegisterViewModel
import kotlinx.coroutines.launch

class ReservationViewModel(private val repository: ReservationRepository): ViewModel() {
    val errorMessage = MutableLiveData<String>()
    private val _reservations = MutableLiveData<List<Reservation>>()
    val reservations: LiveData<List<Reservation>> get() = _reservations

    fun reservation(token: String, reservationRequest: ReservationRequest) {
        viewModelScope.launch {
            try {
                repository.reservation(token, reservationRequest)
            } catch (e: Exception) {
                Log.d("ERROR_RESERVATION", e.message.toString())
                errorMessage.postValue(e.message.toString())
            }
        }
    }


    fun fetchReservations(token: String, userId: Int) {
        viewModelScope.launch {
            try {
                val response = repository.getReservations(token,userId)
                if (response.meta.code == 200){
                    _reservations.postValue(response.data.data_reservation)
                } else {
                    errorMessage.postValue("Get reservation failed")
                }
            } catch (e: Exception) {
                Log.d("ERROR_GET_RESERVATION", e.message.toString())
                errorMessage.postValue(e.message.toString())
            }
        }
    }
}


class ReservationViewModelFactory(private val repository: ReservationRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ReservationViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ReservationViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}