package com.techwarezen.tradelearn.ui.admin.students

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.techwarezen.tradelearn.data.model.Student
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * ViewModel for the Manage Students screen
 */
class ManageStudentsViewModel : ViewModel() {

    private val _students = MutableLiveData<List<Student>>()
    val students: LiveData<List<Student>> = _students

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    init {
        // Load initial data
        refreshData()
    }

    /**
     * Refresh students data
     */
    fun refreshData() {
        _isLoading.value = true

        CoroutineScope(Dispatchers.IO).launch {
            try {
                // In a real app, we would fetch data from Firebase or an API
                // For demo purposes, we'll use mock data
                val studentsList = getMockStudents()

                withContext(Dispatchers.Main) {
                    _students.value = studentsList
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
     * Get mock student data for demo purposes
     */
    private fun getMockStudents(): List<Student> {
        return listOf(
            Student("1", "John Doe", "john.doe@example.com", "Class A", 10000.0, true),
            Student("2", "Jane Smith", "jane.smith@example.com", "Class B", 8500.0, true),
            Student("3", "Bob Johnson", "bob.johnson@example.com", "Class A", 12000.0, true),
            Student("4", "Alice Brown", "alice.brown@example.com", "Class C", 9500.0, false),
            Student("5", "Charlie Wilson", "charlie.wilson@example.com", "Class B", 11000.0, true)
        )
    }
}