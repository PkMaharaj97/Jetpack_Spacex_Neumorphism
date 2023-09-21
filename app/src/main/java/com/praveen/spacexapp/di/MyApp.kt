package com.praveen.spacexapp.di

import android.app.Application
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class MyApp : Application() {

    public override fun onCreate(): Unit {
        super.onCreate()
        instance = this
    }
        companion object {
            private lateinit var instance: MyApp
            public fun getInstance(): MyApp = instance
        }

    }
