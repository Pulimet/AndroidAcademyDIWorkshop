package com.academy.di.repo

import com.academy.di.di.Dependencies

class MoviesRepo {
    fun getMovies() = Dependencies.getMovieDao().getMovies()
}