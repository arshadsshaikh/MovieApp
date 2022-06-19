package com.bhushan.movieapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bhushan.movieapp.data.local.dao.MovieDao
import com.bhushan.movieapp.data.model.Movie

@Database(
    entities = [Movie::class],
    version = 1
)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun getMovieDao(): MovieDao
}