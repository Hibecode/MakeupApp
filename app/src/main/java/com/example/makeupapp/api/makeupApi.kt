package com.example.makeupapp.api

import retrofit2.http.GET

interface makeupApi {

    @GET("/api/v1/products.json")
    suspend fun getProducts()
}