package com.academy.network.di

import com.academy.network.services.TmdbApiService
import com.academy.network.utils.NetworkObjectsCreator
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

object NetworkDependencies {
    private const val TMDB_URL = "https://api.themoviedb.org/"

    private lateinit var okHttpClient: OkHttpClient
    private fun getOkHttpClient(logger: HttpLoggingInterceptor.Logger): OkHttpClient {
        if (!::okHttpClient.isInitialized) okHttpClient =
            NetworkObjectsCreator.createOkHttpClient(logger)
        return okHttpClient
    }

    private fun getApiService(okHttpClient: OkHttpClient) =
        NetworkObjectsCreator.createWebService<TmdbApiService>(okHttpClient, TMDB_URL)

    fun getApiService(logger: HttpLoggingInterceptor.Logger): TmdbApiService {
        val okHttpClient = getOkHttpClient(logger)
        return getApiService(okHttpClient)
    }
}