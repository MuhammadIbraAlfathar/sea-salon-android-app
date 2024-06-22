package com.example.seasalonapp.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.seasalonapp.data.model.response.mainservice.Services
import com.example.seasalonapp.databinding.ItemMainServicesBinding


//data class Service(val image: Int, val service: String, val duration: String)
//private val onItemClick: (Services) -> Unit
class ServiceAdapter(private val itemAdapterCallback: ItemAdapterCallback): RecyclerView.Adapter<ServiceAdapter.ServiceViewHolder>() {
    private val listServices: MutableList<Services> = mutableListOf()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ):ServiceViewHolder {
        val binding = ItemMainServicesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ServiceViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ServiceViewHolder, position: Int) {
        val service = listServices[position]
        holder.bind(service, itemAdapterCallback)
//        holder.itemView.setOnClickListener { onItemClick(service) }
    }

    override fun getItemCount(): Int {
        return listServices.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateServices(newServices: List<Services>) {
        listServices.clear()
        listServices.addAll(newServices)
        notifyDataSetChanged()
    }

    class ServiceViewHolder(private val binding: ItemMainServicesBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(service: Services, itemAdapterCallback: ItemAdapterCallback) {

            binding.apply {
                binding.tvDuration.text = service.duration.toString()
                binding.tvServices.text = service.services_name
                Glide.with(itemView.context)
                    .load(service.picturePath)
                    .into(binding.imageViewService)


                root.setOnClickListener { itemAdapterCallback.onClick(service) }
            }

        }

    }

    interface ItemAdapterCallback {
        fun onClick(data: Services)
    }


}