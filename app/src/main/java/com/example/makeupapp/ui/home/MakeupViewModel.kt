package com.example.makeupapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.makeupapp.data.MakeupRepository
import com.example.makeupapp.data.api.Resource
import com.example.makeupapp.data.model.GroupedProductsList
import com.example.makeupapp.data.model.ProductsList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MakeupViewModel @Inject constructor(
    private val repository: MakeupRepository
) : ViewModel() {

    private val _groupedProductsList: MutableLiveData<MutableList<GroupedProductsList>> =
        MutableLiveData()
    val groupedProductsList: LiveData<MutableList<GroupedProductsList>> = _groupedProductsList

    private val _loading: MutableLiveData<Boolean> = MutableLiveData()
    val loading: LiveData<Boolean> = _loading

    init {
        loadProductsList()
    }

    private fun loadProductsList() = viewModelScope.launch {
        repository.getProducts().collect { response ->
            when (response) {
                is Resource.Success -> {
                    _loading.value = false
                    // submit list of products list to RecyclerView for display
                    val data = response.data
                    if (data != null) {
                        _groupedProductsList.value = groupProductsByBrand(data)
                    }
                }
                is Resource.Error -> {
                    // show error message
                    _loading.value = false
                    Timber.tag("HomeFragment-Error").d(response.message)
                }
                is Resource.Loading -> {
                    // show a progress bar
                    _loading.value = true
                }
            }
        }
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
}