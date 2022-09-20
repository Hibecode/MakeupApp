package com.example.makeupapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.makeupapp.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MakeupViewModel @Inject constructor(
    val repository: Repository
): ViewModel() {

}