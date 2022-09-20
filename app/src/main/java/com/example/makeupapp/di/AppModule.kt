package com.example.makeupapp.di

import com.example.makeupapp.api.MakeupApi
import com.example.makeupapp.repository.Repository
import com.example.makeupapp.repository.RepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApi(): MakeupApi {
        return Retrofit.Builder()
            .baseUrl("http://makeup-api.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MakeupApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRepository(api: MakeupApi): Repository {
        return RepositoryImpl(api)
    }




}