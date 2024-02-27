package com.practicum.imdbmovies.ui.movies.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.practicum.imdbmovies.R
import com.practicum.imdbmovies.domain.models.KinopoiskModel

class MoviesAdapter(private val clickListener: MovieClickListener) :
    RecyclerView.Adapter<MovieViewHolder>() {

    var movies = ArrayList<KinopoiskModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view =  LayoutInflater.from(parent.context).inflate(R.layout.list_item_movie, parent, false)
        return MovieViewHolder(view, clickListener)

    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    override fun getItemCount(): Int = movies.size

    interface MovieClickListener {
        fun onMovieClick(movie: KinopoiskModel)
        fun onFavoriteToggleClick(movie: KinopoiskModel)
    }
}