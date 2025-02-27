package com.techwarezen.tradelearn.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView
import com.techwarezen.tradelearn.R
import com.techwarezen.tradelearn.databinding.ActivityMainBinding
import com.techwarezen.tradelearn.ui.auth.LoginActivity
import com.techwarezen.tradelearn.ui.leaderboard.LeaderboardActivity
import com.techwarezen.tradelearn.ui.learning.LearningActivity
import com.techwarezen.tradelearn.ui.main.dashboard.DashboardFragment
import com.techwarezen.tradelearn.ui.profile.ProfileActivity
import com.techwarezen.tradelearn.ui.trading.TradingActivity

/**
 * Main activity for student users
 * Contains the dashboard and navigation to other sections
 */
class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupToolbar()
        setupNavigationDrawer()
        
        // Set default fragment
        if (savedInstanceState == null) {
            loadFragment(DashboardFragment())
            binding.navView.setCheckedItem(R.id.nav_dashboard)
        }
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.appBarMain.toolbar)
        supportActionBar?.title = getString(R.string.dashboard)
    }

    private fun setupNavigationDrawer() {
        val toggle = ActionBarDrawerToggle(
            this,
            binding.drawerLayout,
            binding.appBarMain.toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        binding.navView.setNavigationItemSelectedListener(this)
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_dashboard -> {
                loadFragment(DashboardFragment())
                supportActionBar?.title = getString(R.string.dashboard)
            }
            R.id.nav_trading -> {
                startActivity(Intent(this, TradingActivity::class.java))
            }
            R.id.nav_learning -> {
                startActivity(Intent(this, LearningActivity::class.java))
            }
            R.id.nav_leaderboard -> {
                startActivity(Intent(this, LeaderboardActivity::class.java))
            }
            R.id.nav_profile -> {
                startActivity(Intent(this, ProfileActivity::class.java))
            }
            R.id.nav_logout -> {
                // Perform logout
                // In a real app, we would sign out from Firebase Auth
                val intent = Intent(this, LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                finish()
            }
        }

        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}