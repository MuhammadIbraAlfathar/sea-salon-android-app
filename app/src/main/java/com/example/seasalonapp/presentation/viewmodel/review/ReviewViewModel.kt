package com.example.seasalonapp.presentation.viewmodel.review

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.seasalonapp.data.model.request.ReviewRequest
import com.example.seasalonapp.data.model.response.review.Review
import com.example.seasalonapp.data.repository.review.ReviewRepository
import kotlinx.coroutines.launch

class ReviewViewModel(private val repository: ReviewRepository): ViewModel() {

    val errorMessage = MutableLiveData<String>()
    private val _reviews = MutableLiveData<List<Review>>()
    val reviews: LiveData<List<Review>> get() = _reviews
    fun submitReview(token: String, reviewRequest: ReviewRequest) {
        viewModelScope.launch {
            try {
                repository.submitReview(token, reviewRequest)
                loadViewModel(token)
            } catch (e: Exception) {
                Log.d("ERROR_SUBMIT_REVIEW", e.message.toString())
            }
        }
    }

    fun loadViewModel(token: String) {
        viewModelScope.launch {
            try {
                val response = repository.getReviews(token)
                _reviews.postValue(response.data.reviews.toMutableList())
            } catch (e: Exception) {
                Log.d("ERROR_GET_REVIEW", e.message.toString())
            }
        }
    }

}


class ReviewModelFactory(private val repository: ReviewRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ReviewViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ReviewViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}