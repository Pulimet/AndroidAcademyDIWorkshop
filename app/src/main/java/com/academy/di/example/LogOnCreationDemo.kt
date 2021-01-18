package com.academy.di.example

import android.util.Log
import dagger.Lazy
import kotlinx.coroutines.*
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class LogOnCreationDemo @Inject constructor(
    private val lazySingleton: Lazy<ImLogOnCreation>,
    private val notSingleton: Lazy<ImLogOnCreation>,
) : CoroutineScope {
    override val coroutineContext: CoroutineContext = SupervisorJob() + Dispatchers.IO

    init {
        Log.w("Academy", "LogOnCreationDemo created")
    }

    fun play() {
        launch {
            delay(4000)
            Log.d("Academy", "LogOnCreationDemo play!")

            val singletonLazy1 = lazySingleton.get()
            val singletonLazy2 = lazySingleton.get()
            Log.w(
                "Academy",
                "singletonLazy1: ${singletonLazy1.hashCode()}, singletonLazy2: ${singletonLazy2.hashCode()}"
            )

            val notSingletonLazy1 = notSingleton.get()
            val notSingletonLazy2 = notSingleton.get()
            Log.w(
                "Academy",
                "notSingletonLazy1: ${notSingletonLazy1.hashCode()}, notSingletonLazy2: ${notSingletonLazy2.hashCode()}"
            )
        }
    }
}