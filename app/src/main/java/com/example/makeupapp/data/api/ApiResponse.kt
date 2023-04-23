package com.example.makeupapp.data.api

import com.example.makeupapp.data.model.ProductsList
import retrofit2.Response
import timber.log.Timber

abstract class ApiResponse {

    //This checks whether the response is successful or not and sends the result
    suspend fun safeApiCall(
        apiCall: suspend () -> Response<ProductsList>
    ): Resource<ProductsList> {
        try {
            Timber.tag("ApiResponse").d("checking...")
            val response = apiCall()
            if (response.isSuccessful) {
                val body = response.body()
                body?.let {
                    return Resource.Success(body)
                }
            }
            return error("${response.code()} ${response.message()}")
        } catch (e: Exception) {
            return error(e.message ?: e.toString())
        }
    }

    private fun error(errorMessage: String): Resource<ProductsList> =
        Resource.Error("Api call failed $errorMessage")
}

sealed class Resource<T>(
    val data: T? = null, val message: String? = null
) {
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(message: String?, data: T? = null) : Resource<T>(data, message)
    class Loading<T> : Resource<T>()
}