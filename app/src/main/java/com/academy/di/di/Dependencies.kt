package com.academy.di.di

import com.academy.db.di.DbDependencies
import com.academy.di.App
import com.academy.di.repo.MoviesRepo
import com.academy.di.utils.OkHttpLogs
import com.academy.network.di.NetworkDependencies
import okhttp3.logging.HttpLoggingInterceptor

object Dependencies {
    private lateinit var logger: HttpLoggingInterceptor.Logger
    private fun getLogger(): HttpLoggingInterceptor.Logger {
        if (!::logger.isInitialized) logger = OkHttpLogs()
        return logger
    }

    fun getApiServices() = NetworkDependencies.getApiService(getLogger())

    fun getMoviesRepo() = MoviesRepo()
    fun getMovieDao() = DbDependencies.getMovieDao(App.applicationContext())
}