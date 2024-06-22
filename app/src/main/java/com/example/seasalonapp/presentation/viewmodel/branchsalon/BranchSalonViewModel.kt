package com.example.seasalonapp.presentation.viewmodel.branchsalon

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.seasalonapp.data.model.response.branchsalon.BranchSalon
import com.example.seasalonapp.data.repository.branchsalon.BranchSalonRepository
import com.example.seasalonapp.data.repository.review.ReviewRepository
import com.example.seasalonapp.presentation.viewmodel.review.ReviewViewModel
import kotlinx.coroutines.launch

class BranchSalonViewModel(private val repository: BranchSalonRepository): ViewModel() {
    private val _branchSalons = MutableLiveData<List<BranchSalon>>()
    val branchSalons: LiveData<List<BranchSalon>> get() = _branchSalons

    fun loadBranchSalon() {
        viewModelScope.launch {
            try {
                val response = repository.getBranchSalon()
                _branchSalons.postValue(response.data.branch_salon)
            }catch (e: Exception) {
                Log.d("ERROR_GET_BRANCH_SALON", e.message.toString())
            }
        }
    }
}


class BranchSalonViewModelFactory(private val repository: BranchSalonRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BranchSalonViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return BranchSalonViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}