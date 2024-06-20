package com.example.seasalonapp.data.repository.mainservices


import MainServiceResponse
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.seasalonapp.data.model.response.mainservice.Services
import com.example.seasalonapp.data.remote.ApiService
import com.example.seasalonapp.data.remote.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainServiceRepository {
    private val api = RetrofitClient.instance

    suspend fun getMainServices(): Response<MainServiceResponse> {
        return api.getMainServices()
    }
}