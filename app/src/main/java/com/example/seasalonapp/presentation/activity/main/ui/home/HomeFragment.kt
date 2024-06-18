package com.example.seasalonapp.presentation.activity.main.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.seasalonapp.R
import com.example.seasalonapp.databinding.FragmentHomeBinding
import com.example.seasalonapp.presentation.adapter.ImageSlideAdapter

@Suppress("UNREACHABLE_CODE")
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this ).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root


        val images = listOf(R.drawable.img_1, R.drawable.img_2, R.drawable.img_3, R.drawable.img_4)
        val adapter = ImageSlideAdapter(images)
        binding.viewPager.adapter = adapter

        binding.dotsIndicator.attachTo(binding.viewPager)
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}