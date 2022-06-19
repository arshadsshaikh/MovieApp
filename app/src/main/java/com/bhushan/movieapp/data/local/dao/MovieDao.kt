package com.bhushan.movieapp.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bhushan.movieapp.data.model.Movie
import com.bhushan.movieapp.utils.Constants.TABLE_NAME

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: Movie)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<Movie>?)

    @Delete
    suspend fun removeMovie(movie: Movie)

    @Query("DELETE FROM $TABLE_NAME WHERE id=:id")
    suspend fun deleteMovieById(id : Int)

    @Query("SELECT * FROM $TABLE_NAME")
    suspend fun getAllMovies() : List<Movie>

    @Query("SELECT EXISTS (SELECT 1 FROM $TABLE_NAME WHERE id = :id)")
    fun movieExist(id: Int): Boolean

}