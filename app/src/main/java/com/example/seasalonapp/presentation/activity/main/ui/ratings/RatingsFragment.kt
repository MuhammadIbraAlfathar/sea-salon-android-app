package com.example.seasalonapp.presentation.activity.main.ui.ratings

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.seasalonapp.data.model.request.ReviewRequest
import com.example.seasalonapp.data.repository.review.ReviewRepository
import com.example.seasalonapp.databinding.FragmentRatingBinding
import com.example.seasalonapp.helper.PreferenceHelper
import com.example.seasalonapp.presentation.adapter.ReviewAdapter
import com.example.seasalonapp.presentation.viewmodel.review.ReviewModelFactory
import com.example.seasalonapp.presentation.viewmodel.review.ReviewViewModel

class RatingsFragment : Fragment() {

    private var _binding: FragmentRatingBinding? = null
    private lateinit var reviewViewModel: ReviewViewModel

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
//    private val reviewsList = mutableListOf<Review>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
//        val dashboardViewModel =
//            ViewModelProvider(this).get(RatingsViewModel::class.java)


        val repository = ReviewRepository()
        val factory = ReviewModelFactory(repository)
        reviewViewModel = ViewModelProvider(this, factory)[ReviewViewModel::class.java]


        _binding = FragmentRatingBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        val textView: TextView = binding.textDashboard
//        dashboardViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }
        return root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        val name = PreferenceHelper.getUser(requireContext())?.name
        val id_user = PreferenceHelper.getUser(requireContext())?.id
        val token = PreferenceHelper.getAccessToken(requireContext()).toString()

        binding.submitButton.setOnClickListener {

            val comment = binding.commentInput.text.toString()
            val rating = binding.ratingBar.rating

            val reviewRequest = ReviewRequest(
                user_id = id_user!!.toInt(),
                rating = rating.toInt(),
                comment = comment,
                name = name.toString()
            )

            reviewViewModel.submitReview(token, reviewRequest)


            if (name != null) {
                if (name.isNotEmpty() && comment.isNotEmpty()) {
//                    reviewsList.add(Review(name, comment, rating))
                    binding.reviewsRecyclerView.adapter?.notifyDataSetChanged()
                    binding.commentInput.text.clear()
                    binding.ratingBar.rating = 0f
                }
            }
        }

        reviewViewModel.reviews.observe(viewLifecycleOwner, Observer { reviews ->
            binding.reviewsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
            binding.reviewsRecyclerView.adapter = ReviewAdapter(reviews)
        })

        reviewViewModel.loadViewModel(token)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}