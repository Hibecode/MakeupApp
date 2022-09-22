package com.example.makeupapp.utils

import android.app.Application
import com.example.makeupapp.BuildConfig
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class App: Application() {

    override fun onCreate() {
        super.onCreate()

        //Timber set up
        if(BuildConfig.DEBUG){
            Timber.plant(Timber.DebugTree())
        }

    }
}