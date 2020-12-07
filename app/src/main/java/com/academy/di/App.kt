package com.academy.di

import android.app.Application
import android.util.Log
import com.academy.di.di.AppComponent
import com.academy.di.di.DaggerAppComponent
import com.academy.di.di.DbModule

class App : Application() {
    init {
        Log.e("Academy", "App created")
    }

    override fun onCreate() {
        super.onCreate()
        component = DaggerAppComponent.builder()
            .dbModule(DbModule(applicationContext))
            .build()
    }

    companion object {
        lateinit var component: AppComponent
    }
}