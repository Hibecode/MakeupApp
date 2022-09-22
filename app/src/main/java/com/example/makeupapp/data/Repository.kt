package com.example.makeupapp.data

import android.app.Application
import com.example.makeupapp.api.MakeupApi
import com.example.makeupapp.models.ProductsList
import com.example.makeupapp.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class Repository @Inject constructor(
    private val api: MakeupApi,
    private val appContext: Application
): ApiResponse() {



    suspend fun getProducts(): Flow<Resource<ProductsList>> {
        return flow {
            // Emit the data to the stream
            emit(
                safeApiCall { api.getProducts() }
            )
        }.flowOn(Dispatchers.IO) // Use the IO thread for this Flow
    }
}