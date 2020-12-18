package com.academy.di.di

import androidx.datastore.preferences.createDataStore
import com.academy.db.di.DbDependencies
import com.academy.di.App
import com.academy.di.repo.MoviesRepo
import com.academy.di.utils.OkHttpLogs
import com.academy.network.di.NetworkDependencies

object Dependencies {
    fun getMovieFavoriteDao() = DbDependencies.getMovieFavoriteDao(App.applicationContext())
}