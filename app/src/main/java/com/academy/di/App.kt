package com.academy.di

import android.app.Application
import android.util.Log
import com.academy.di.di.AppComponent
import com.academy.di.di.DaggerAppComponent
import com.academy.di.di.modules.DbModule
import com.academy.di.di.modules.MoviesModule
import com.academy.di.di.modules.NetworkModule

class App : Application() {
    companion object {
        lateinit var component: AppComponent
    }

    init {
        Log.e("Academy", "App created")
    }

    override fun onCreate() {
        super.onCreate()
        buildDaggerComponent()
    }

    private fun buildDaggerComponent() {
        component = DaggerAppComponent.builder()
            .moviesModule(MoviesModule())
            .dbModule(DbModule(applicationContext))
            .networkModule(NetworkModule())
            .build()
    }
}