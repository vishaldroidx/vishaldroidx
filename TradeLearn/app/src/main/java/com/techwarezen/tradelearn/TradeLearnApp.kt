package com.techwarezen.tradelearn

import android.app.Application
import com.google.firebase.FirebaseApp

/**
 * Main Application class for TradeLearn app
 * Initializes Firebase and other app-wide configurations
 */
class TradeLearnApp : Application() {

    override fun onCreate() {
        super.onCreate()
        
        // Initialize Firebase
        FirebaseApp.initializeApp(this)
        
        // Initialize other app-wide configurations here
    }
}