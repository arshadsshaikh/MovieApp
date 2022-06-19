package com.bhushan.movieapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bhushan.movieapp.R
import com.bhushan.movieapp.data.model.Movie
import com.bhushan.movieapp.databinding.ItemMovieBinding
import com.bhushan.movieapp.utils.Constants
import com.bumptech.glide.Glide


class MoviesViewAdapter :
    PagingDataAdapter<Movie, MoviesViewAdapter.ViewHolder>(MOVIE_COMPARATOR) {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemMovieBinding.bind(itemView)
        fun bind(movie: Movie) {
            binding.apply {
                Glide.with(itemView)
                    .load(Constants.IMAGE_BASE_URL + movie.poster_path)
                    .into(movieImage)

                textMovieName.text = movie.title
                textMovieRelease.text = movie.release_date
                itemView.setOnClickListener {
                    val bundle = bundleOf(Constants.movie to movie)
                    it.findNavController()
                        .navigate(R.id.action_movieListFragment_to_movieDetailsFragment, bundle)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }

    companion object {
        private val MOVIE_COMPARATOR = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean =
                oldItem == newItem
        }
    }
}