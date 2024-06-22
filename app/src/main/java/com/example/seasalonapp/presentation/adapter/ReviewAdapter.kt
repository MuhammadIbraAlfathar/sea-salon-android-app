package com.example.seasalonapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.seasalonapp.data.model.response.review.Review
import com.example.seasalonapp.databinding.ItemMainServicesBinding
import com.example.seasalonapp.databinding.ItemReviewBinding



class ReviewAdapter(private val review: MutableList<Review>): RecyclerView.Adapter<ReviewAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ):ViewHolder {
        val binding = ItemReviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    fun updateReviews(newReviews: List<Review>) {
        review.clear()
        review.addAll(newReviews)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val review = review[position]
        holder.bind(review)
    }

    override fun getItemCount(): Int {
        return review.size
    }

    class ViewHolder(private val binding: ItemReviewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(review: Review) {

            binding.apply {
                binding.reviewName.text = review.name
                binding.reviewComment.text = review.comment
                binding.reviewRatingBar.rating = review.rating.toFloat()
            }

        }

    }


}