package com.practicum.imdbmovies.ui.movies.adapter

import android.graphics.drawable.Drawable
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.practicum.imdbmovies.R
import com.practicum.imdbmovies.databinding.ListItemMovieBinding
import com.practicum.imdbmovies.domain.models.KinopoiskModel

class MovieViewHolder(item: View, private val clickListener: MoviesAdapter.MovieClickListener) : RecyclerView.ViewHolder(item) {

     private val binding = ListItemMovieBinding.bind(item)

    fun bind(movie: KinopoiskModel) = with(binding) {
        Glide.with(itemView)
            .load(movie.image)
            .into(cover)

        title.text = movie.name
        description.text = movie.description
        fovarite.setImageDrawable(getFavoriteTogglerDrawable(movie.inFavorite))

        itemView.setOnClickListener {
            clickListener.onMovieClick(movie)
        }

        fovarite.setOnClickListener {
            clickListener.onFavoriteToggleClick(movie)
        }

    }

    private fun getFavoriteTogglerDrawable(inFavorite : Boolean) : Drawable? {
        return  itemView.context.getDrawable(
            if(inFavorite) R.drawable.button_like_true else R.drawable.button__like
        )
    }
}