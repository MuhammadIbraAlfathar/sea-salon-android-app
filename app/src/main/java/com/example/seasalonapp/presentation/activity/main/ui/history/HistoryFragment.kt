package com.example.seasalonapp.presentation.activity.main.ui.history

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.seasalonapp.R
import com.example.seasalonapp.databinding.FragmentHistoryBinding
import com.example.seasalonapp.databinding.FragmentHomeBinding
import com.example.seasalonapp.presentation.adapter.BranchSalon
import com.example.seasalonapp.presentation.adapter.BranchSalonAdapter

class HistoryFragment : Fragment() {

    private var _binding: FragmentHistoryBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val branch = listOf(
            BranchSalon(R.drawable.branch_img1, "SEA Salon A", "Jl. Otista 2 No.15 F, RT.3/RW.9, Bidara Cina, Kecamatan Jatinegara, Kota Jakarta Timur, Daerah Khusus Ibukota Jakarta 13330, Indonesia", "09:00", "20:00"),
            BranchSalon(R.drawable.branch_img2, "SEA Salon B", "l. Saabun No.21 5, RT.5/RW.2, Bidara Cina, Kecamatan Jatinegara", "10:00", "22:00"),
            BranchSalon(R.drawable.branch_img3, "SEA Salon C", "Jl. Cipinang Cempedak II Kel No.2A 11, RT.11/RW.6, Cipinang Cempedak", "08:00", "20:00")
        )

        binding.rcList.layoutManager = LinearLayoutManager(context)
        binding.rcList.adapter = BranchSalonAdapter(branch)
    }



}