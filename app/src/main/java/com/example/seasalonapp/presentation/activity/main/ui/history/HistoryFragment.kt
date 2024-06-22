package com.example.seasalonapp.presentation.activity.main.ui.history

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.seasalonapp.R
import com.example.seasalonapp.databinding.FragmentHistoryBinding
import com.example.seasalonapp.presentation.adapter.History
import com.example.seasalonapp.presentation.adapter.HistoryReservationAdapter

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

        val history = listOf(
            History(R.drawable.img_1, "Haircut and Stylish", "19:00", "20:00"),
            History(R.drawable.img_2, "Manicure Padicure", "19:00", "20:00"),
            History(R.drawable.img_3, "Facial Treatment", "19:00", "20:00"),
        )

        binding.rcList.layoutManager = LinearLayoutManager(context)
        binding.rcList.adapter = HistoryReservationAdapter(history)
    }



}