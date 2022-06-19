package com.bhushan.movieapp.data.api

import com.bhushan.movieapp.data.model.Movie
import com.bhushan.movieapp.data.model.MovieResponse
import com.bhushan.movieapp.data.model.VideoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int = 1,
        @Query("language") lang: String = "en-US"
    ): Response<MovieResponse>

    @GET("movie/{movie_id}")
    suspend fun getMovieById(
        @Path("movie_id") movie_id: Int,
        @Query("api_key") apiKey: String,
        @Query("language") lang: String = "en-US",
    ): Response<Movie>

    @GET("movie/{movie_id}/videos")
    suspend fun getVideos(
        @Path("movie_id") movie_id: Int,
        @Query("api_key") apiKey: String,
        @Query("language") lang: String = "en-US",
    ): Response<VideoResponse>

}