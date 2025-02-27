package com.techwarezen.tradelearn.ui.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.techwarezen.tradelearn.R
import com.techwarezen.tradelearn.databinding.ActivityLoginBinding
import com.techwarezen.tradelearn.ui.admin.AdminDashboardActivity
import com.techwarezen.tradelearn.ui.main.MainActivity

/**
 * Login activity for user authentication
 * Handles both student and admin/teacher logins
 */
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize ViewModel
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        setupUI()
        observeViewModel()
    }

    private fun setupUI() {
        // Set up login button click listener
        binding.btnLogin.setOnClickListener {
            val username = binding.etUsername.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            if (validateInputs(username, password)) {
                viewModel.login(username, password)
            }
        }
    }

    private fun observeViewModel() {
        // Observe login result
        viewModel.loginResult.observe(this) { result ->
            when (result) {
                is LoginResult.Success -> {
                    // Navigate based on user role
                    if (result.isAdmin) {
                        navigateToAdminDashboard()
                    } else {
                        navigateToStudentDashboard()
                    }
                }
                is LoginResult.Error -> {
                    // Show error message
                    Toast.makeText(this, result.message, Toast.LENGTH_SHORT).show()
                }
                is LoginResult.Loading -> {
                    // Show loading state
                    binding.btnLogin.isEnabled = !result.isLoading
                    binding.progressBar.visibility = if (result.isLoading) android.view.View.VISIBLE else android.view.View.GONE
                }
            }
        }
    }

    private fun validateInputs(username: String, password: String): Boolean {
        var isValid = true

        // Validate username
        if (username.isEmpty()) {
            binding.tilUsername.error = "Username cannot be empty"
            isValid = false
        } else {
            binding.tilUsername.error = null
        }

        // Validate password
        if (password.isEmpty()) {
            binding.tilPassword.error = "Password cannot be empty"
            isValid = false
        } else {
            binding.tilPassword.error = null
        }

        return isValid
    }

    private fun navigateToStudentDashboard() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun navigateToAdminDashboard() {
        val intent = Intent(this, AdminDashboardActivity::class.java)
        startActivity(intent)
        finish()
    }
}