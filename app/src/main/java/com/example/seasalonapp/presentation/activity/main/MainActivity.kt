package com.example.seasalonapp.presentation.activity.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toolbar
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.seasalonapp.R
import com.example.seasalonapp.databinding.ActivityMainBinding
import com.example.seasalonapp.helper.PreferenceHelper
import com.example.seasalonapp.presentation.activity.auth.LoginActivity
import com.example.seasalonapp.presentation.activity.auth.RegisterActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_ratings, R.id.navigation_profile, R.id.navigation_history
            )
        )

        val token = PreferenceHelper.getAccessToken(this).toString()
        if (token.isEmpty()) {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        toolbar.visibility = View.GONE
    }
}