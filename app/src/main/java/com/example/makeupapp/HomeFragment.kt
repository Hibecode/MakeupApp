package com.example.makeupapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.makeupapp.adapters.ParentBrandAdapter
import com.example.makeupapp.databinding.FragmentHomeBinding
import com.example.makeupapp.models.GroupedProductsList
import com.example.makeupapp.models.ProductsList
import com.example.makeupapp.models.ProductsListItem
import com.example.makeupapp.utils.Resource
import com.example.makeupapp.viewmodel.MakeupViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber


@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MakeupViewModel by viewModels()
    private lateinit var adapter: ParentBrandAdapter

    private var isLoading = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root

        setUpRecyclerView()
        loadData()

        return view
   }

    private fun loadData() {
        viewModel.loadProductsList()
        viewModel.response.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgressBar()
                    // submit list of products list to RecyclerView for display
                    val data = response.data
                    if (data != null) {
                        val list = groupProductsByBrand(data)
                        adapter.differ.submitList(list)
                    }
                }
                is Resource.Error -> {
                    // show error message
                    hideProgressBar()
                    Timber.tag("HomeFragment-Error").d(response.message)

                }
                is Resource.Loading -> {
                    // show a progress bar
                    showProgressBar()
                }
            }
        }
    }

    private fun showProgressBar() {
        binding.progressBar.visibility = View.VISIBLE
        isLoading = true
    }

    private fun hideProgressBar() {
        binding.progressBar.visibility = View.INVISIBLE
        isLoading = false
    }


    // Sets up recyclerView
    private fun setUpRecyclerView() {
        binding.rvParent.adapter = adapter
        binding.rvParent.layoutManager = LinearLayoutManager(activity)
    }

    // This groups products with similar brands together
    private fun groupProductsByBrand(productsList: ProductsList): MutableList<GroupedProductsList> {
        val groupedProductsList = productsList.groupBy { it.brand }
        val listOfGroups: MutableList<GroupedProductsList> = mutableListOf()

        //Convert products to GroupedProductsList class
        groupedProductsList.values.toList().forEach {
            listOfGroups.add(GroupedProductsList(it[0].brand, it))
        }

        listOfGroups.reverse()
        return listOfGroups
    }




    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}