package com.techwarezen.tradelearn.ui.admin.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.techwarezen.tradelearn.databinding.FragmentAdminDashboardBinding

/**
 * Admin dashboard fragment that displays statistics and management options
 */
class AdminDashboardFragment : Fragment() {

    private var _binding: FragmentAdminDashboardBinding? = null
    private val binding get() = _binding!!
    
    private lateinit var viewModel: AdminDashboardViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAdminDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        // Initialize ViewModel
        viewModel = ViewModelProvider(this).get(AdminDashboardViewModel::class.java)
        
        setupUI()
        observeViewModel()
    }

    private fun setupUI() {
        // Set up refresh layout
        binding.swipeRefresh.setOnRefreshListener {
            viewModel.refreshData()
        }
        
        // Set up card click listeners
        binding.cardManageStudents.setOnClickListener {
            // Navigate to manage students fragment
            (requireActivity() as? com.techwarezen.tradelearn.ui.admin.AdminDashboardActivity)?.let { activity ->
                activity.onNavigationItemSelected(activity.binding.navView.menu.findItem(com.techwarezen.tradelearn.R.id.nav_manage_students))
            }
        }
        
        binding.cardReports.setOnClickListener {
            // Navigate to reports fragment (not implemented in this demo)
        }
        
        binding.cardNotifications.setOnClickListener {
            // Navigate to notifications fragment (not implemented in this demo)
        }
    }

    private fun observeViewModel() {
        // Observe total students count
        viewModel.totalStudents.observe(viewLifecycleOwner) { count ->
            binding.tvTotalStudentsCount.text = count.toString()
        }
        
        // Observe active students count
        viewModel.activeStudents.observe(viewLifecycleOwner) { count ->
            binding.tvActiveStudentsCount.text = count.toString()
        }
        
        // Observe total transactions count
        viewModel.totalTransactions.observe(viewLifecycleOwner) { count ->
            binding.tvTotalTransactionsCount.text = count.toString()
        }
        
        // Observe loading state
        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.swipeRefresh.isRefreshing = isLoading
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}