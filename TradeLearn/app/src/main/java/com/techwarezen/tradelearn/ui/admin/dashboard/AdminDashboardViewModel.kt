package com.techwarezen.tradelearn.ui.admin.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * ViewModel for the Admin Dashboard screen
 */
class AdminDashboardViewModel : ViewModel() {

    private val _totalStudents = MutableLiveData<Int>()
    val totalStudents: LiveData<Int> = _totalStudents

    private val _activeStudents = MutableLiveData<Int>()
    val activeStudents: LiveData<Int> = _activeStudents

    private val _totalTransactions = MutableLiveData<Int>()
    val totalTransactions: LiveData<Int> = _totalTransactions

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
                val totalStudentsCount = 25
                val activeStudentsCount = 18
                val totalTransactionsCount = 156

                withContext(Dispatchers.Main) {
                    _totalStudents.value = totalStudentsCount
                    _activeStudents.value = activeStudentsCount
                    _totalTransactions.value = totalTransactionsCount
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
}