package com.academy.db.di

import android.content.Context
import androidx.room.Room
import com.academy.db.MovieDao
import com.academy.db.MovieDatabase

object DbDependencies {
    private lateinit var roomDb: MovieDatabase
    private fun getRoomDb(context: Context): MovieDatabase {
        if (!DbDependencies::roomDb.isInitialized) roomDb =
            Room.databaseBuilder(context, MovieDatabase::class.java, "movies_database").build()
        return roomDb
    }

    fun getMovieDao(context: Context): MovieDao = getRoomDb(context).movieDao()
}