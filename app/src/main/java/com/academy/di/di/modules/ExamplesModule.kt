package com.academy.di.di.modules

import com.academy.di.example.ImLogOnCreation
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class ExamplesModule {
    @Provides
    @Singleton
    @Named("LogSingleton")
    fun getSingletonLogOnCreation() = ImLogOnCreation("Singleton")

    @Provides
    @Named("LogNotSingleton")
    fun getNotSingletonLogOnCreation() = ImLogOnCreation("Not Singleton")
}