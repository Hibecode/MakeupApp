package com.example.makeupapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.makeupapp.R
import com.example.makeupapp.databinding.FragmentHomeBinding
import com.example.makeupapp.ui.home.adapter.ParentBrandAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MakeupViewModel by viewModels()
    private val adapter by lazy {
        ParentBrandAdapter {
            val action = HomeFragmentDirections.actionHomeFragmentToMakeupProductFragment(it)
            findNavController().navigate(action)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root

        setupRecyclerview()
        observeData()

        return view
    }

    private fun setupRecyclerview() {
        binding.rvParent.adapter = adapter
    }

    private fun observeData() {
        viewModel.groupedProductsList.observe(viewLifecycleOwner) { list ->
            adapter.differ.submitList(list)
        }

        viewModel.loading.observe(viewLifecycleOwner) { loading ->
            if (loading) showProgressBar() else hideProgressBar()
        }
    }

    private fun showProgressBar() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        binding.progressBar.visibility = View.INVISIBLE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}