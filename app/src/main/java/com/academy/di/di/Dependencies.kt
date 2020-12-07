package com.academy.di.di

import com.academy.di.repo.MoviesRepo
import com.academy.di.utils.OkHttpLogs
import com.academy.network.di.NetworkDependencies

object Dependencies {
    private val logger = OkHttpLogs()
    val moviesRepo = MoviesRepo()

    fun getApiServices() = NetworkDependencies.getApiService(logger)
}