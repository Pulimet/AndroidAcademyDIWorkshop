package com.academy.di

import android.app.Application
import android.content.Context
import android.util.Log
import com.academy.di.di.Injector

class App : Application() {
    init {
        Log.e("Academy", "App created")
        instance = this
    }

    companion object {
        private var instance: App? = null
        fun applicationContext(): Context {
            return instance!!.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()
        Injector.buildDaggerAppComponent(this)
    }
}