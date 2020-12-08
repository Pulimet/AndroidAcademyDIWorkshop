package com.academy.di.repo

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelChildren
import kotlin.coroutines.CoroutineContext

class SettingsRepo : CoroutineScope {
    init {
        Log.w("Academy", "SettingsRepo init")
    }

    override val coroutineContext: CoroutineContext = SupervisorJob() + Dispatchers.IO

    fun onCleared() {
        coroutineContext.cancelChildren()
        Log.w("Academy", "SettingsRepo onCleared")
    }
}