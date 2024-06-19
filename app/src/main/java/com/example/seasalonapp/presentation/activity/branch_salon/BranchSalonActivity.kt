package com.example.seasalonapp.presentation.activity.branch_salon

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.seasalonapp.R
import com.example.seasalonapp.databinding.ActivityBranchSalonBinding
import com.example.seasalonapp.databinding.ActivityLoginBinding
import com.example.seasalonapp.presentation.adapter.BranchSalon
import com.example.seasalonapp.presentation.adapter.BranchSalonAdapter


class BranchSalonActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBranchSalonBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityBranchSalonBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)



        val branch = listOf(
            BranchSalon(R.drawable.branch_img1, "SEA Salon A", "Jl. Otista 2 No.15 F, RT.3/RW.9, Bidara Cina, Kecamatan Jatinegara, Kota Jakarta Timur, Daerah Khusus Ibukota Jakarta 13330, Indonesia", "09:00", "20:00"),
            BranchSalon(R.drawable.branch_img2, "SEA Salon B", "l. Saabun No.21 5, RT.5/RW.2, Bidara Cina, Kecamatan Jatinegara", "10:00", "22:00"),
            BranchSalon(R.drawable.branch_img3, "SEA Salon C", "Jl. Cipinang Cempedak II Kel No.2A 11, RT.11/RW.6, Cipinang Cempedak", "08:00", "20:00")
        )

        binding.rcList.layoutManager = LinearLayoutManager(this)
        binding.rcList.adapter = BranchSalonAdapter(branch)

    }
}