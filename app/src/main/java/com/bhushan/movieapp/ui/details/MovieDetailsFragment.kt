package com.bhushan.movieapp.ui.details

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bhushan.movieapp.R
import com.bhushan.movieapp.data.model.Movie
import com.bhushan.movieapp.databinding.FragmentMovieDetailsBinding
import com.bhushan.movieapp.ui.dialog.VideoPlayerDialog
import com.bhushan.movieapp.utils.Constants
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailsFragment : Fragment(), View.OnClickListener {

    private lateinit var movie: Movie
    private val viewModel: MovieDetailsViewModel by viewModels()
    private lateinit var binding: FragmentMovieDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movie_details, container, false)
        binding = FragmentMovieDetailsBinding.bind(view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movie = requireArguments().get(Constants.movie) as Movie
        viewModel.movieName.value = movie.title
        viewModel.movie.value = movie
        binding.fabPlayButton.setOnClickListener(this)
        loadData()
        viewModel.getMovieDetails(movie.id)
        viewModel.getVideos(movie.id)
    }


    @SuppressLint("SetTextI18n")
    private fun loadData() {
        viewModel.movie.observe(requireActivity()) {
            binding.apply {
                textMovieName.text = it!!.title
                textDescription.text = it.overview
                Glide.with(binding.root).load(Constants.IMAGE_BASE_URL + movie.backdrop_path)
                    .into(detailsBannerImage)
                Glide.with(binding.root)
                    .load(Constants.IMAGE_BASE_URL + movie.poster_path)
                    .into(imagePoster)
            }
        }
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.fabPlayButton -> {
                if (viewModel.videos.value != null && viewModel.videos.value!!.results.size != 0) {
                    val videoDialog = VideoPlayerDialog(viewModel.videos.value!!.results[0].key)
                    videoDialog.show(childFragmentManager, "Video Dialog")
                } else {
                    Toast.makeText(context, "Video not found!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}