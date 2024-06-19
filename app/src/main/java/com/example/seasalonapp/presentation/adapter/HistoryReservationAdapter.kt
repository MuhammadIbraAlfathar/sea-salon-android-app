package com.example.seasalonapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.seasalonapp.databinding.ItemHistoryReservationBinding


data class History(val image: Int, val service: String, val durationStart: String, val durationEnd: String)
class HistoryReservationAdapter(private val history: List<History>): RecyclerView.Adapter<HistoryReservationAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HistoryReservationAdapter.ViewHolder {
        val binding = ItemHistoryReservationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HistoryReservationAdapter.ViewHolder(binding)
    }

    class ViewHolder(private val binding: ItemHistoryReservationBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(history: History) {

            binding.apply {
                binding.serviceHistory.text = history.service
                binding.timeEndHistory.text = history.durationEnd
                binding.timeStartHistory.text = history.durationStart
                Glide.with(itemView.context)
                    .load(history.image)
                    .into(binding.ivHistoryService)
            }

        }
    }

    override fun onBindViewHolder(holder: HistoryReservationAdapter.ViewHolder, position: Int) {
        val history = history[position]
        holder.bind(history)
    }

    override fun getItemCount(): Int {
        return history.size
    }
}