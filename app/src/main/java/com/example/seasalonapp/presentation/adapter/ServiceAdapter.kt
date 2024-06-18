package com.example.seasalonapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.seasalonapp.databinding.ItemMainServicesBinding


data class Service(val image: Int, val service: String, val duration: String)
class ServiceAdapter(private val services: List<Service>): RecyclerView.Adapter<ServiceAdapter.ServiceViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ):ServiceViewHolder {
        val binding = ItemMainServicesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ServiceViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ServiceViewHolder, position: Int) {
        val service = services[position]
        holder.bind(service)
    }

    override fun getItemCount(): Int {
        return services.size
    }

    class ServiceViewHolder(private val binding: ItemMainServicesBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(service: Service) {

            binding.apply {
                binding.tvDuration.text = service.duration
                binding.tvServices.text = service.service
                Glide.with(itemView.context)
                    .load(service.image)
                    .into(binding.imageViewService)
            }

        }

    }


}