package com.techwarezen.tradelearn.ui.main.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.techwarezen.tradelearn.data.model.Stock
import com.techwarezen.tradelearn.data.model.Transaction
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * ViewModel for the Dashboard screen
 */
class DashboardViewModel : ViewModel() {

    private val _virtualBalance = MutableLiveData<Double>()
    val virtualBalance: LiveData<Double> = _virtualBalance

    private val _trendingStocks = MutableLiveData<List<Stock>>()
    val trendingStocks: LiveData<List<Stock>> = _trendingStocks

    private val _recentTransactions = MutableLiveData<List<Transaction>>()
    val recentTransactions: LiveData<List<Transaction>> = _recentTransactions

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    init {
        // Load initial data
        refreshData()
    }

    /**
     * Refresh all dashboard data
     */
    fun refreshData() {
        _isLoading.value = true

        CoroutineScope(Dispatchers.IO).launch {
            try {
                // In a real app, we would fetch data from Firebase or an API
                // For demo purposes, we'll use mock data
                val balance = 10000.0
                val stocks = getMockStocks()
                val transactions = getMockTransactions()

                withContext(Dispatchers.Main) {
                    _virtualBalance.value = balance
                    _trendingStocks.value = stocks
                    _recentTransactions.value = transactions
                }
            } catch (e: Exception) {
                // Handle error
            } finally {
                withContext(Dispatchers.Main) {
                    _isLoading.value = false
                }
            }
        }
    }

    /**
     * Get mock stock data for demo purposes
     */
    private fun getMockStocks(): List<Stock> {
        return listOf(
            Stock("AAPL", "Apple Inc.", 150.25, 2.5),
            Stock("MSFT", "Microsoft Corp.", 290.10, 1.2),
            Stock("GOOGL", "Alphabet Inc.", 2750.50, -0.8),
            Stock("AMZN", "Amazon.com Inc.", 3350.75, 1.7),
            Stock("TSLA", "Tesla Inc.", 725.60, -2.1)
        )
    }

    /**
     * Get mock transaction data for demo purposes
     */
    private fun getMockTransactions(): List<Transaction> {
        return listOf(
            Transaction("AAPL", "Buy", 5, 148.25, System.currentTimeMillis() - 86400000),
            Transaction("MSFT", "Sell", 2, 288.50, System.currentTimeMillis() - 172800000),
            Transaction("GOOGL", "Buy", 1, 2740.30, System.currentTimeMillis() - 259200000)
        )
    }
}