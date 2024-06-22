package com.example.seasalonapp.presentation.activity.auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.seasalonapp.data.model.request.LoginRequest
import com.example.seasalonapp.data.repository.auth.LoginRepository
import com.example.seasalonapp.databinding.ActivityLoginBinding
import com.example.seasalonapp.helper.PreferenceHelper
import com.example.seasalonapp.presentation.activity.main.MainActivity
import com.example.seasalonapp.presentation.viewmodel.auth.LoginViewModel
import com.example.seasalonapp.presentation.viewmodel.auth.LoginViewModelFactory

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: LoginViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        val token = PreferenceHelper.getAccessToken(this)
        if (token != null) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val repository = LoginRepository()
        val factory = LoginViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory)[LoginViewModel::class.java]


        setupObserver()


        binding.submitButton.setOnClickListener {
            val email = binding.inputEmail.text.toString()
            val password = binding.inputPassword.text.toString()
            viewModel.login(LoginRequest(email, password))
        }


        binding.tvRegisterMessage.setOnClickListener {
            navigateToRegister()
        }
    }

    private fun setupObserver() {
        viewModel.loginResponse.observe(this) { response ->
            response?.let { dataResponse ->
                val setToken = dataResponse.data.access_token
                val user = dataResponse.data.user

                //save token
                PreferenceHelper.saveAccessToken(this, setToken)

                //save data user
                PreferenceHelper.saveUser(this, user)


                Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)

                val savedUser = PreferenceHelper.getUser(this)
                savedUser?.let {
                    Log.d("Saved User", "Name: ${it.name}, Email: ${it.email}, ID: ${it.id}")
                }
            }
        }

        viewModel.errorMessage.observe(this) { message ->
            message?.let {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
                Log.d("ERR", it)
            }
        }
    }

    fun navigateToRegister() {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }
}