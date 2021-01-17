package com.academy.di

import android.app.Application
import android.util.Log
import com.academy.di.di.Injector

class App : Application() {
    init {
        Log.e("Academy", "App created")
    }

    override fun onCreate() {
        super.onCreate()
        Injector.buildDaggerAppComponent(this)
    }
}