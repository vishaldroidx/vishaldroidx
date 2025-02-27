package com.techwarezen.tradelearn.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.techwarezen.tradelearn.R
import com.techwarezen.tradelearn.ui.auth.LoginActivity

/**
 * Splash screen activity that displays the app logo and transitions to the login screen
 */
class SplashActivity : AppCompatActivity() {

    private val SPLASH_DELAY = 2000L // 2 seconds

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // Delay and then navigate to the login screen
        Handler(Looper.getMainLooper()).postDelayed({
            navigateToLogin()
        }, SPLASH_DELAY)
    }

    private fun navigateToLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish() // Close the splash activity
    }
}