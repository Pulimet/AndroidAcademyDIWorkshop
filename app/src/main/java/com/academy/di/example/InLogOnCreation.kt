package com.academy.di.example

import android.util.Log

class ImLogOnCreation(name: String) {
    init {
        Log.d("Academy", "ImLogOnCreation: You called me: $name, and my hash: ${this.hashCode()}")
    }
}