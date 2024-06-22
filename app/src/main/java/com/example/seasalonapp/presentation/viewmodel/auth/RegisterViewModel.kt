package com.example.seasalonapp.presentation.viewmodel.auth

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.seasalonapp.data.model.request.RegisterRequest
import com.example.seasalonapp.data.repository.auth.LoginRepository
import com.example.seasalonapp.data.repository.auth.RegisterRepository
import kotlinx.coroutines.launch

class RegisterViewModel(private val repository: RegisterRepository): ViewModel() {
    val errorMessage = MutableLiveData<String>()

    fun register(registerRequest: RegisterRequest) {
        viewModelScope.launch {
            try {
                repository.register(registerRequest)
            } catch (e: Exception) {
                Log.d("ERROR_REGISTER", e.message.toString())
                errorMessage.postValue(e.message.toString())
            }
        }
    }
}

class RegisterViewModelFactory(private val repository: RegisterRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RegisterViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return RegisterViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}