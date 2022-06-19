package com.bhushan.movieapp.hiltdi

import com.bhushan.movieapp.data.api.MovieService
import com.bhushan.movieapp.data.local.MovieDatabase
import com.bhushan.movieapp.data.local.dao.MovieDao
import com.bhushan.movieapp.data.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
class MovieModule {

    @Provides
    fun provideMovieDao(database: MovieDatabase): MovieDao = database.getMovieDao()


    @Provides
    fun provideMovieRepository(movieDao: MovieDao, movieService: MovieService): MovieRepository =
        MovieRepository(movieDao, movieService)
}