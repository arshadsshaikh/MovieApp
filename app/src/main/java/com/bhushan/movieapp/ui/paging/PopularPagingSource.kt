package com.bhushan.movieapp.ui.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.bhushan.movieapp.data.api.MovieService
import com.bhushan.movieapp.data.local.dao.MovieDao
import com.bhushan.movieapp.data.model.Movie
import com.bhushan.movieapp.utils.Constants
import java.io.IOException
import retrofit2.HttpException

private const val DEFAULT_PAGE = 1

class PopularPagingSource(
    private val movieService: MovieService,
    private val movieDao: MovieDao
) : PagingSource<Int, Movie>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {

        val position = params.key ?: DEFAULT_PAGE

        return try {
            val response = movieService.getPopularMovies(Constants.API_KEY, position)
            val data = response.body()!!.results
            movieDao.insertMovies(data)
            pageMovieData(data, position)

        } catch (exception: IOException) {
            checkOfflineData(position, exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }

    private suspend fun checkOfflineData(
        position: Int,
        exception: IOException
    ): LoadResult<Int, Movie> {
        val data = movieDao.getAllMovies()
        return if (data.isNotEmpty()) {
            pageMovieData(data, position)
        } else {
            LoadResult.Error(exception)
        }
    }

    private fun pageMovieData(data: List<Movie>, position: Int): LoadResult<Int, Movie> {
        return LoadResult.Page(
            data = data,
            prevKey = if (position == DEFAULT_PAGE) null else position - 1,
            nextKey = if (data.isEmpty()) null else position + 1
        )
    }


    override fun getRefreshKey(state: PagingState<Int, Movie>): Int {
        return 1
    }

}