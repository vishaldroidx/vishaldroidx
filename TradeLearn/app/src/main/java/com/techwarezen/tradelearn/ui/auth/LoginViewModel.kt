package com.techwarezen.tradelearn.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

/**
 * ViewModel for handling login logic
 */
class LoginViewModel : ViewModel() {

    private val _loginResult = MutableLiveData<LoginResult>()
    val loginResult: LiveData<LoginResult> = _loginResult

    // Firebase Auth instance
    private val auth = FirebaseAuth.getInstance()

    /**
     * Attempt to login with the provided credentials
     */
    fun login(username: String, password: String) {
        // Set loading state
        _loginResult.value = LoginResult.Loading(true)

        // In a real app, we would use Firebase Authentication
        // For demo purposes, we'll use a simple check
        CoroutineScope(Dispatchers.IO).launch {
            try {
                // Simulate network delay
                kotlinx.coroutines.delay(1000)

                // Check if admin credentials
                val isAdmin = username == "admin" && password == "admin123"
                
                // Check if student credentials
                val isStudent = username == "student" && password == "student123"

                withContext(Dispatchers.Main) {
                    if (isAdmin || isStudent) {
                        _loginResult.value = LoginResult.Success(isAdmin)
                    } else {
                        _loginResult.value = LoginResult.Error("Invalid username or password")
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    _loginResult.value = LoginResult.Error(e.message ?: "An error occurred")
                }
            } finally {
                withContext(Dispatchers.Main) {
                    _loginResult.value = LoginResult.Loading(false)
                }
            }
        }
    }
}

/**
 * Sealed class to represent different login states
 */
sealed class LoginResult {
    data class Success(val isAdmin: Boolean) : LoginResult()
    data class Error(val message: String) : LoginResult()
    data class Loading(val isLoading: Boolean) : LoginResult()
}