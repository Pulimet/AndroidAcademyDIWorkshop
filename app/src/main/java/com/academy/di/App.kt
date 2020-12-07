package com.academy.di

import android.app.Application
import android.util.Log
import com.academy.di.di.AppComponent
import com.academy.di.di.DaggerAppComponent
import com.academy.di.di.modules.DbModule
import com.academy.di.di.modules.MoviesModule
import com.academy.di.di.modules.NetworkModule

class App : Application() {
    init {
        Log.e("Academy", "App created")
    }

    override fun onCreate() {
        super.onCreate()
        component = DaggerAppComponent.builder()
            .moviesModule(MoviesModule())
            .dbModule(DbModule(applicationContext))
            .networkModule(NetworkModule())
            .build()
    }

    companion object {
        lateinit var component: AppComponent
    }
}