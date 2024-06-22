package com.example.seasalonapp.presentation.viewmodel.mainservices

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.seasalonapp.data.model.response.mainservice.Services
import com.example.seasalonapp.data.repository.auth.LoginRepository
import com.example.seasalonapp.data.repository.mainservices.MainServiceRepository
import com.example.seasalonapp.presentation.viewmodel.auth.LoginViewModel
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: MainServiceRepository) : ViewModel() {

    val _dataResponse = MutableLiveData<List<Services>>()
    val dataResponse: LiveData<List<Services>> = _dataResponse
    val errorMessage = MutableLiveData<String>()

    fun getDataServices(){
        viewModelScope.launch {
            try {
                val response = repository.getMainServices()
                if (response.isSuccessful) {
                    _dataResponse.value = response.body()?.data?.data?.toMutableList()
                } else {
                    errorMessage.postValue("Failed")
                }
            } catch (e: Exception) {
                errorMessage.postValue(e.message)
            }
        }
    }

}


class HomeVieModelFactory(private val repository: MainServiceRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HomeViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}