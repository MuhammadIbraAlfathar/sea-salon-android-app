package com.example.seasalonapp.presentation.activity.auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.seasalonapp.data.model.request.RegisterRequest
import com.example.seasalonapp.data.repository.auth.RegisterRepository
import com.example.seasalonapp.databinding.ActivityRegisterBinding
import com.example.seasalonapp.presentation.viewmodel.auth.RegisterViewModel
import com.example.seasalonapp.presentation.viewmodel.auth.RegisterViewModelFactory

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var viewModel: RegisterViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val repository = RegisterRepository()
        val factory = RegisterViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory)[RegisterViewModel::class.java]

        binding.submitButton.setOnClickListener {

            val name = binding.inputName.text
            val email = binding.inputEmail.text
            val password = binding.inputPassword.text
            val phoneNumber = binding.inputPhone.text.toString()

            val request = RegisterRequest(
                name = name.toString(),
                email = email.toString(),
                password = password.toString(),
                phone_number = phoneNumber
            )

            viewModel.register(request)

            Toast.makeText(this, "Register Success", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        viewModel.errorMessage.observe(this) { message ->
            message?.let {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
                Log.d("ERROR_REGISTER", it)
            }
        }
    }
}