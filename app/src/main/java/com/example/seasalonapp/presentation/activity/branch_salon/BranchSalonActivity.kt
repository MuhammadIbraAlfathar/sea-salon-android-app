package com.example.seasalonapp.presentation.activity.branch_salon

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.seasalonapp.data.repository.branchsalon.BranchSalonRepository
import com.example.seasalonapp.databinding.ActivityBranchSalonBinding
import com.example.seasalonapp.presentation.adapter.BranchSalonAdapter
import com.example.seasalonapp.presentation.viewmodel.branchsalon.BranchSalonViewModel
import com.example.seasalonapp.presentation.viewmodel.branchsalon.BranchSalonViewModelFactory


class BranchSalonActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBranchSalonBinding
    private lateinit var viewModel: BranchSalonViewModel
    private lateinit var branchAdapter: BranchSalonAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityBranchSalonBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val repository = BranchSalonRepository()
        val factory = BranchSalonViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory)[BranchSalonViewModel::class.java]
        binding.rcList.layoutManager = LinearLayoutManager(this)
        branchAdapter = BranchSalonAdapter(emptyList())
        binding.rcList.adapter = branchAdapter


        viewModel.loadBranchSalon()

        viewModel.branchSalons.observe(this, Observer { branchSalon ->
            branchAdapter = BranchSalonAdapter(branchSalon)
            binding.rcList.adapter = branchAdapter
        })

    }
}