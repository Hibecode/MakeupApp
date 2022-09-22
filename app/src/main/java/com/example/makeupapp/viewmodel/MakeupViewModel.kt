package com.example.makeupapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.makeupapp.data.Repository
import com.example.makeupapp.models.ProductsList
import com.example.makeupapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MakeupViewModel @Inject constructor(
    private val repository: Repository
): ViewModel() {

    private val _response: MutableLiveData<Resource<ProductsList>> = MutableLiveData()
    val response: LiveData<Resource<ProductsList>> = _response


    fun loadProductsList() = viewModelScope.launch {

        repository.getProducts().collect{ values ->
            // Set Resource class to loading to indicate to progress bar
            _response.value = Resource.Loading()
            Timber.tag("ViewModel").d(values.toString())
            _response.value = values
        }
    }

}