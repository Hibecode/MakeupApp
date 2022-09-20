package com.example.makeupapp.repository

import com.example.makeupapp.api.MakeupApi

class RepositoryImpl(
    private val api: MakeupApi
): Repository {
    override suspend fun getProducts() {

    }
}