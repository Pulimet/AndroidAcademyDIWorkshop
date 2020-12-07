package com.academy.di.di

import android.content.Context
import androidx.room.Room
import com.academy.db.MovieDao
import com.academy.db.MovieDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DbModule(private val context: Context) {

    @Provides
    @Singleton
    fun getRoomDb(): MovieDatabase = Room.databaseBuilder(context, MovieDatabase::class.java, "movies_database").build()

    @Provides
    @Singleton
    fun getMovieDao(db: MovieDatabase): MovieDao = db.movieDao()
}