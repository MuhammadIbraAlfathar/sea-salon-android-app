package com.example.seasalonapp.presentation.activity.main.ui.ratings

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.seasalonapp.databinding.FragmentRatingBinding
import com.example.seasalonapp.helper.PreferenceHelper
import com.example.seasalonapp.presentation.adapter.Review
import com.example.seasalonapp.presentation.adapter.ReviewAdapter

class RatingsFragment : Fragment() {

    private var _binding: FragmentRatingBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val reviewsList = mutableListOf<Review>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(RatingsViewModel::class.java)

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

        binding.reviewsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.reviewsRecyclerView.adapter = ReviewAdapter(reviewsList)

        val name = PreferenceHelper.getUser(requireContext())?.name

        binding.submitButton.setOnClickListener {

            val comment = binding.commentInput.text.toString()
            val rating = binding.ratingBar.rating


            if (name != null) {
                if (name.isNotEmpty() && comment.isNotEmpty()) {
                    reviewsList.add(Review(name, comment, rating))
                    binding.reviewsRecyclerView.adapter?.notifyDataSetChanged()
                    binding.commentInput.text.clear()
                    binding.ratingBar.rating = 0f
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}