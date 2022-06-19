package com.bhushan.movieapp.ui.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bhushan.movieapp.data.model.Movie
import com.bhushan.movieapp.data.model.VideoResponse
import com.bhushan.movieapp.data.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    private val _name = MutableLiveData("Movie Name")
    private val _movie = MutableLiveData<Movie>()

    var movieName: MutableLiveData<String> = _name
    var movie: MutableLiveData<Movie> = _movie
    var videos: MutableLiveData<VideoResponse> = MutableLiveData()

    fun getMovieDetails(movie_id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val apiResponse = movieRepository.getMovieDetails(movie_id)
            movie.postValue(apiResponse)
        }
    }

    fun getVideos(movie_id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val apiResponse = movieRepository.getVideos(movie_id)
                videos.postValue(apiResponse)
            } catch (e: Exception) {

            }
        }
    }
}