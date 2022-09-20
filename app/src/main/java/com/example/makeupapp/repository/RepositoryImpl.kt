package com.example.makeupapp.repository

import android.app.Application
import com.example.makeupapp.api.MakeupApi

class RepositoryImpl(
    private val api: MakeupApi,
    private val appContext: Application
): Repository {

    override suspend fun getProducts() {
        return api.getProducts()
    }
}