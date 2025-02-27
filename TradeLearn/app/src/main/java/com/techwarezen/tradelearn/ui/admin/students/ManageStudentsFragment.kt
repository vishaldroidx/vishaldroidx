package com.techwarezen.tradelearn.ui.admin.students

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.techwarezen.tradelearn.R
import com.techwarezen.tradelearn.databinding.FragmentManageStudentsBinding

/**
 * Fragment for managing student accounts
 */
class ManageStudentsFragment : Fragment() {

    private var _binding: FragmentManageStudentsBinding? = null
    private val binding get() = _binding!!
    
    private lateinit var viewModel: ManageStudentsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentManageStudentsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        // Initialize ViewModel
        viewModel = ViewModelProvider(this).get(ManageStudentsViewModel::class.java)
        
        setupUI()
        observeViewModel()
        
        // Show the FAB
        requireActivity().findViewById<FloatingActionButton>(R.id.fab_add_student).visibility = View.VISIBLE
    }

    private fun setupUI() {
        // Set up refresh layout
        binding.swipeRefresh.setOnRefreshListener {
            viewModel.refreshData()
        }
        
        // Set up FAB click listener
        requireActivity().findViewById<FloatingActionButton>(R.id.fab_add_student).setOnClickListener {
            // Show add student dialog (not implemented in this demo)
            // In a real app, we would show a dialog to add a new student
        }
    }

    private fun observeViewModel() {
        // Observe students list
        viewModel.students.observe(viewLifecycleOwner) { students ->
            // In a real app, we would use a RecyclerView adapter
            // For demo purposes, we'll just update the text
            if (students.isNotEmpty()) {
                binding.tvNoStudents.visibility = View.GONE
                binding.rvStudents.visibility = View.VISIBLE
                // Set up adapter here
            } else {
                binding.tvNoStudents.visibility = View.VISIBLE
                binding.rvStudents.visibility = View.GONE
            }
        }
        
        // Observe loading state
        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.swipeRefresh.isRefreshing = isLoading
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Hide the FAB
        requireActivity().findViewById<FloatingActionButton>(R.id.fab_add_student).visibility = View.GONE
        _binding = null
    }
}