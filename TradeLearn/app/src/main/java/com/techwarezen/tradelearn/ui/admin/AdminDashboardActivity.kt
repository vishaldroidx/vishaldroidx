package com.techwarezen.tradelearn.ui.admin

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView
import com.techwarezen.tradelearn.R
import com.techwarezen.tradelearn.databinding.ActivityAdminDashboardBinding
import com.techwarezen.tradelearn.ui.admin.dashboard.AdminDashboardFragment
import com.techwarezen.tradelearn.ui.admin.students.ManageStudentsFragment
import com.techwarezen.tradelearn.ui.auth.LoginActivity

/**
 * Main activity for admin/teacher users
 * Contains the admin dashboard and navigation to other admin sections
 */
class AdminDashboardActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: ActivityAdminDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupToolbar()
        setupNavigationDrawer()
        
        // Set default fragment
        if (savedInstanceState == null) {
            loadFragment(AdminDashboardFragment())
            binding.navView.setCheckedItem(R.id.nav_admin_dashboard)
        }
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.appBarAdmin.toolbar)
        supportActionBar?.title = getString(R.string.admin)
    }

    private fun setupNavigationDrawer() {
        val toggle = ActionBarDrawerToggle(
            this,
            binding.drawerLayout,
            binding.appBarAdmin.toolbar,
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
            R.id.nav_admin_dashboard -> {
                loadFragment(AdminDashboardFragment())
                supportActionBar?.title = getString(R.string.admin)
            }
            R.id.nav_manage_students -> {
                loadFragment(ManageStudentsFragment())
                supportActionBar?.title = getString(R.string.manage_users)
            }
            R.id.nav_admin_logout -> {
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