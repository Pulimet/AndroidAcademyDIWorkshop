package com.academy.di.example

import android.util.Log
import com.academy.di.di.Injector
import dagger.Lazy
import kotlinx.coroutines.*
import javax.inject.Inject
import javax.inject.Provider
import kotlin.coroutines.CoroutineContext

class LogOnCreationDemo @Inject constructor(
    private val lazySingleton: Lazy<ImLogOnCreation>,
    private val lazySingleton2: Lazy<ImLogOnCreation>,
    private val notSingleton: Lazy<ImLogOnCreation>,
    private val notSingleton2: Lazy<ImLogOnCreation>,
    private val providerExample: Provider<ImLogOnCreation>
) : CoroutineScope {
    override val coroutineContext: CoroutineContext = SupervisorJob() + Dispatchers.IO

    init {
        Log.w("Academy", "LogOnCreationDemo created")
        Injector.appComponent.inject(this)
    }

    fun play() {
        launch {
            delay(4000)
            Log.d("Academy", "LogOnCreationDemo play!")

            val singletonLazy1 = lazySingleton.get()
            val singletonLazy2 = lazySingleton.get()
            val singletonLazy3 = lazySingleton2.get()
            Log.w(
                "Academy",
                "singletonLazy1: ${singletonLazy1.hashCode()}, singletonLazy2: ${singletonLazy2.hashCode()}, singletonLazy3: ${singletonLazy3.hashCode()}"
            )

            val notSingletonLazy1 = notSingleton.get()
            val notSingletonLazy2 = notSingleton.get()
            val notSingletonLazy3 = notSingleton2.get()
            Log.w(
                "Academy",
                "notSingletonLazy1: ${notSingletonLazy1.hashCode()}, notSingletonLazy2: ${notSingletonLazy2.hashCode()}, notSingletonLazy3: ${notSingletonLazy3.hashCode()}"
            )

            val provider1 = providerExample.get()
            val provider2 = providerExample.get()
            val provider3 = providerExample.get()
            Log.w(
                "Academy",
                "provider1: ${provider1.hashCode()}, provider2: ${provider2.hashCode()}, provider3: ${provider3.hashCode()}"
            )

        }
    }
}