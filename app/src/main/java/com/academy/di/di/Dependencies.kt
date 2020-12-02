package com.academy.di.di

import com.academy.db.DbDependencies
import com.academy.di.App
import com.academy.di.repo.MoviesRepo

object Dependencies {
    fun getMoviesRepo() = MoviesRepo()
    fun getMovieDao() = DbDependencies.getMovieDao(App.applicationContext())
}