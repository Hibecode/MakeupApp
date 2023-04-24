package com.example.makeupapp.data

import com.example.makeupapp.data.api.ApiResponse
import com.example.makeupapp.data.api.MakeupApiService
import com.example.makeupapp.data.api.Resource
import com.example.makeupapp.data.model.ProductsList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MakeupRepository @Inject constructor(private val api: MakeupApiService) : ApiResponse() {

    suspend fun getProducts(): Flow<Resource<ProductsList>> {
        return flow {
            // Emit the data to the stream
            emit(safeApiCall { api.getProducts() })
        }.flowOn(Dispatchers.IO) // Use the IO thread for this Flow
    }
}