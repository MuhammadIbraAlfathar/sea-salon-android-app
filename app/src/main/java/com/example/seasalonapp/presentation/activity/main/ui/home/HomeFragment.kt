package com.example.seasalonapp.presentation.activity.main.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.seasalonapp.R
import com.example.seasalonapp.data.repository.mainservices.MainServiceRepository
import com.example.seasalonapp.databinding.FragmentHomeBinding
import com.example.seasalonapp.helper.PreferenceHelper
import com.example.seasalonapp.presentation.activity.branch_salon.BranchSalonActivity
import com.example.seasalonapp.presentation.activity.reservation.ReservationActivity
import com.example.seasalonapp.presentation.adapter.ImageSlideAdapter
import com.example.seasalonapp.presentation.adapter.ServiceAdapter
import com.example.seasalonapp.presentation.viewmodel.mainservices.HomeVieModelFactory
import com.example.seasalonapp.presentation.viewmodel.mainservices.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

@Suppress("UNREACHABLE_CODE")
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var homeViewModel: HomeViewModel
    private val serviceAdapter = ServiceAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
//        val homeViewModel =
//            ViewModelProvider(this ).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root


        val images = listOf(R.drawable.img_1, R.drawable.img_2, R.drawable.img_3, R.drawable.img_4)
        val adapter = ImageSlideAdapter(images)
        binding.viewPager.adapter = adapter

        binding.dotsIndicator.attachTo(binding.viewPager)
        binding.toolBar.toolbar.visibility = View.GONE

        val layoutManager = GridLayoutManager(context, 2)


        binding.rcList.layoutManager = layoutManager
        binding.rcList.adapter = serviceAdapter


        val repository = MainServiceRepository()
        val factory = HomeVieModelFactory(repository)
        homeViewModel = ViewModelProvider(this, factory).get(HomeViewModel::class.java)

        homeViewModel.getDataServices()

        setupObserver()



        // get name user
        val name = PreferenceHelper.getUser(requireContext())?.name
        binding.tvName.text = name.toString()

        binding.reservationButton.setOnClickListener {
            val intent = Intent(activity, ReservationActivity::class.java)
            startActivity(intent)
        }

        binding.branchsButton.setOnClickListener {
            val intent = Intent(activity, BranchSalonActivity::class.java)
            startActivity(intent)
        }

        return root
    }

    private fun setupObserver() {
        homeViewModel.dataResponse.observe(viewLifecycleOwner) { data ->
            serviceAdapter.updateServices(data)
        }
        homeViewModel.errorMessage.observe(viewLifecycleOwner, Observer { message ->
            message?.let {
                Toast.makeText(requireContext(),it, Toast.LENGTH_SHORT).show()
                Log.d("ERR", it)
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}