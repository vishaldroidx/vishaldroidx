package com.techwarezen.tradelearn.ui.main.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.techwarezen.tradelearn.databinding.FragmentDashboardBinding

/**
 * Dashboard fragment that displays the student's virtual balance, trending stocks, and recent transactions
 */
class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!
    
    private lateinit var viewModel: DashboardViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        // Initialize ViewModel
        viewModel = ViewModelProvider(this).get(DashboardViewModel::class.java)
        
        setupUI()
        observeViewModel()
    }

    private fun setupUI() {
        // Set up refresh layout
        binding.swipeRefresh.setOnRefreshListener {
            viewModel.refreshData()
        }
    }

    private fun observeViewModel() {
        // Observe virtual balance
        viewModel.virtualBalance.observe(viewLifecycleOwner) { balance ->
            binding.tvBalanceAmount.text = String.format("$%.2f", balance)
        }
        
        // Observe trending stocks
        viewModel.trendingStocks.observe(viewLifecycleOwner) { stocks ->
            // In a real app, we would use a RecyclerView adapter
            // For demo purposes, we'll just update the text
            if (stocks.isNotEmpty()) {
                binding.tvNoTrendingStocks.visibility = View.GONE
                binding.rvTrendingStocks.visibility = View.VISIBLE
                // Set up adapter here
            } else {
                binding.tvNoTrendingStocks.visibility = View.VISIBLE
                binding.rvTrendingStocks.visibility = View.GONE
            }
        }
        
        // Observe recent transactions
        viewModel.recentTransactions.observe(viewLifecycleOwner) { transactions ->
            // In a real app, we would use a RecyclerView adapter
            // For demo purposes, we'll just update the text
            if (transactions.isNotEmpty()) {
                binding.tvNoTransactions.visibility = View.GONE
                binding.rvRecentTransactions.visibility = View.VISIBLE
                // Set up adapter here
            } else {
                binding.tvNoTransactions.visibility = View.VISIBLE
                binding.rvRecentTransactions.visibility = View.GONE
            }
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