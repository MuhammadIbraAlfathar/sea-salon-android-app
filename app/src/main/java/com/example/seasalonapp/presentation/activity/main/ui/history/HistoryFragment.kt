package com.example.seasalonapp.presentation.activity.main.ui.history

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.seasalonapp.data.model.response.reservation.Reservation
import com.example.seasalonapp.data.repository.reservation.ReservationRepository
import com.example.seasalonapp.databinding.FragmentHistoryBinding
import com.example.seasalonapp.helper.PreferenceHelper
import com.example.seasalonapp.presentation.activity.reservation.DetailHistoryActivity
import com.example.seasalonapp.presentation.adapter.HistoryReservationAdapter
import com.example.seasalonapp.presentation.viewmodel.reservation.ReservationViewModel
import com.example.seasalonapp.presentation.viewmodel.reservation.ReservationViewModelFactory

class HistoryFragment : Fragment(), HistoryReservationAdapter.ItemAdapterCallback {

    private var _binding: FragmentHistoryBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var viewModel: ReservationViewModel
    private lateinit var reservationAdapter: HistoryReservationAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val repository = ReservationRepository()
        val viewModelFactory = ReservationViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[ReservationViewModel::class.java]

        val userId = PreferenceHelper.getUser(requireContext())?.id
        val token = PreferenceHelper.getAccessToken(requireContext()).toString()

        viewModel.fetchReservations(token, userId!!)
        binding.rcList.layoutManager = LinearLayoutManager(context)

        viewModel.reservations.observe(viewLifecycleOwner) { reservation ->
            reservationAdapter = HistoryReservationAdapter(this, reservation)
            binding.rcList.adapter = reservationAdapter
        }
    }

    override fun onClick(data: Reservation) {
        val detail = Intent(activity, DetailHistoryActivity::class.java).apply {
            putExtra("data_reservation", data)
        }

        startActivity(detail)
    }
}