package com.bhushan.movieapp.ui.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.bhushan.movieapp.data.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PopularViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    fun fetchPopular() = movieRepository.getPopularMovieResult().cachedIn(viewModelScope)
}