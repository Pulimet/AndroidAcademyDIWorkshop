package com.academy.db

import android.content.Context
import androidx.room.Room

object DbDependencies {
    private lateinit var roomDb: MovieDatabase
    private fun getRoomDb(context: Context): MovieDatabase {
        if (!::roomDb.isInitialized) roomDb =
            Room.databaseBuilder(context, MovieDatabase::class.java, "movies_database").build()
        return roomDb
    }

    fun getMovieDao(context: Context): MovieDao = getRoomDb(context).movieDao()
}