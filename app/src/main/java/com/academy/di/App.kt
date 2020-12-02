package com.academy.di

import android.app.Application
import android.content.Context
import android.util.Log

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
}