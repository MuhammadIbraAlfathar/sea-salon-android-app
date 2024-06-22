package com.example.seasalonapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.seasalonapp.data.model.response.branchsalon.BranchSalon
import com.example.seasalonapp.databinding.ItemBranchSalonBinding
import com.example.seasalonapp.databinding.ItemMainServicesBinding


class BranchSalonAdapter(private val branchs: List<BranchSalon>): RecyclerView.Adapter<BranchSalonAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BranchSalonAdapter.ViewHolder {
        val binding = ItemBranchSalonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BranchSalonAdapter.ViewHolder(binding)
    }

    class ViewHolder(private val binding: ItemBranchSalonBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(branch: BranchSalon) {

            binding.apply {
                binding.tvNameSalon.text = branch.branch_name
                binding.tvAddress.text = branch.branch_address
                binding.timeOpening.text = branch.opening_time
                binding.timeClosing.text = branch.closing_time
                Glide.with(itemView.context)
                    .load(branch.picturePath)
                    .into(binding.ivBranchSalon)
            }

        }
    }

    override fun onBindViewHolder(holder: BranchSalonAdapter.ViewHolder, position: Int) {
        val branch = branchs[position]
        holder.bind(branch)
    }

    override fun getItemCount(): Int {
        return branchs.size
    }
}