package com.bhushan.movieapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.bhushan.movieapp.data.api.BaseApiRequest
import com.bhushan.movieapp.data.api.MovieService
import com.bhushan.movieapp.data.local.dao.MovieDao
import com.bhushan.movieapp.ui.paging.PopularPagingSource
import com.bhushan.movieapp.utils.Constants
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val movieDao: MovieDao,
    private val movieService: MovieService
) : BaseApiRequest() {

    // Get Movie Details
    suspend fun getMovieDetails(movie_id: Int) = apiRequest {
        movieService.getMovieById(movie_id, Constants.API_KEY)
    }

    // Get Videos
    suspend fun getVideos(movie_id: Int) = apiRequest {
        movieService.getVideos(movie_id, Constants.API_KEY)
    }

    fun getPopularMovieResult() = Pager(
        config = PagingConfig(
            pageSize = 20,
            maxSize = 100
        ),
        pagingSourceFactory = {
            PopularPagingSource(movieService, movieDao)
        }
    ).liveData
}