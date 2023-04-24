package com.example.makeupapp.data.api

import com.example.makeupapp.data.model.ProductsList
import retrofit2.Response
import retrofit2.http.GET

interface MakeupApiService {

    @GET("/api/v1/products.json")
    suspend fun getProducts(): Response<ProductsList>
}