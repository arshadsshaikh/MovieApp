package com.bhushan.movieapp.ui.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bhushan.movieapp.R
import com.bhushan.movieapp.databinding.FragmentMovieListBinding
import com.bhushan.movieapp.ui.adapter.MoviesViewAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieListFragment : Fragment() {

    private lateinit var binding: FragmentMovieListBinding
    private val viewModel: PopularViewModel by viewModels()

    private lateinit var movieAdapter: MoviesViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_movie_list, container, false)
        binding = FragmentMovieListBinding.bind(view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movieAdapter = MoviesViewAdapter()
        binding.movieRecyclerView.adapter = movieAdapter
        fetchPopular()
    }

    private fun fetchPopular() {
        viewModel.fetchPopular().observe(viewLifecycleOwner) {
            movieAdapter.submitData(viewLifecycleOwner.lifecycle, it)
        }
    }
}