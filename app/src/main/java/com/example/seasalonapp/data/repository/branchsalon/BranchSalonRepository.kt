package com.example.seasalonapp.data.repository.branchsalon

import com.example.seasalonapp.data.model.response.branchsalon.BranchSalonResponse
import com.example.seasalonapp.data.remote.RetrofitClient

class BranchSalonRepository {
    private val api = RetrofitClient.instance
    suspend fun getBranchSalon(): BranchSalonResponse {
        return api.getBranchSalon()
    }
}