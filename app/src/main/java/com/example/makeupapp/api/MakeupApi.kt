package com.example.makeupapp.api

import com.example.makeupapp.models.ProductsList
import retrofit2.Response
import retrofit2.http.GET

interface MakeupApi {

    @GET("/api/v1/products.json")
    suspend fun getProducts(): Response<ProductsList>
}