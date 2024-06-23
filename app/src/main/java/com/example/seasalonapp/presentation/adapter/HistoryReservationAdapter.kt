package com.example.seasalonapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.seasalonapp.data.model.response.mainservice.Services
import com.example.seasalonapp.data.model.response.reservation.Reservation
import com.example.seasalonapp.databinding.ItemHistoryReservationBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class HistoryReservationAdapter(private val itemAdapterCallback: ItemAdapterCallback, private val history: List<Reservation>): RecyclerView.Adapter<HistoryReservationAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HistoryReservationAdapter.ViewHolder {
        val binding = ItemHistoryReservationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HistoryReservationAdapter.ViewHolder(binding)
    }

    class ViewHolder(private val binding: ItemHistoryReservationBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(history: Reservation, itemAdapterCallback: ItemAdapterCallback) {

            binding.apply {
                binding.serviceHistory.text = history.services
                binding.timeEndHistory.text = history.time_end
                binding.timeStartHistory.text = history.time_start
                binding.dateHistory.text = history.date
                binding.tvCreated.text = formatDate(history.created_at)

                root.setOnClickListener { itemAdapterCallback.onClick(history) }
            }

        }


        private fun formatDate(dateString: String): String {
            val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'", Locale.getDefault())
            val outputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val date: Date? = inputFormat.parse(dateString)
            return date?.let { outputFormat.format(it) } ?: dateString
        }
    }



    override fun onBindViewHolder(holder: HistoryReservationAdapter.ViewHolder, position: Int) {
        val history = history[position]
        holder.bind(history, itemAdapterCallback)
    }

    override fun getItemCount(): Int {
        return history.size
    }

    interface ItemAdapterCallback {
        fun onClick(data: Reservation)
    }
}