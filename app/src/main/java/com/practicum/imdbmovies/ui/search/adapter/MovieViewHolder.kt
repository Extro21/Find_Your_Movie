package com.practicum.imdbmovies.ui.search.adapter

import android.graphics.drawable.Drawable
import android.os.Build
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.practicum.imdbmovies.R
import com.practicum.imdbmovies.databinding.ListItemMovieBinding
import com.practicum.imdbmovies.domain.models.KinopoiskModel

class MovieViewHolder(private val item: View, private val clickListener: MoviesAdapter.MovieClickListener) : RecyclerView.ViewHolder(item) {

    private val binding = ListItemMovieBinding.bind(item)

    fun bind(movie: KinopoiskModel) = with(binding) {
        Glide.with(itemView)
            .load(movie.image)
            .into(cover)

        title.text = movie.name
        description.text = movie.description
        val rating = movie.rating
        ratingLabel.text = rating
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            when {
                rating.toDouble() < 6 -> ratingLabel.setTextColor(item.context.getColor(R.color.red))
                rating.toDouble() < 8 -> ratingLabel.setTextColor(item.context.getColor(R.color.gray))
                else -> ratingLabel.setTextColor(item.context.getColor(R.color.dark_green))
            }
        }

        fovarite.setImageDrawable(getFavoriteTogglerDrawable(movie.inFavorite))

        itemView.setOnClickListener {
            clickListener.onMovieClick(movie)
        }

        fovarite.setOnClickListener {
            clickListener.onFavoriteToggleClick(movie)
        }

    }

    private fun getFavoriteTogglerDrawable(inFavorite : Boolean) : Drawable? {
        return if (inFavorite) {
            ContextCompat.getDrawable(itemView.context, R.drawable.button_like_true)
        } else {
            ContextCompat.getDrawable(itemView.context, R.drawable.button__like)
        }
    }
}