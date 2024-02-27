package com.practicum.imdbmovies.ui.movies.adapter

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.practicum.imdbmovies.R
import com.practicum.imdbmovies.databinding.ListItemMovieBinding
import com.practicum.imdbmovies.domain.models.KinopoiskModel

class MovieViewHolder(item: View) : RecyclerView.ViewHolder(item) {

     private val binding = ListItemMovieBinding.bind(item)

//    var cover: ImageView = itemView.findViewById(R.id.cover)
//    var title: TextView = itemView.findViewById(R.id.title)
//    var description: TextView = itemView.findViewById(R.id.description)


    fun bind(movie: KinopoiskModel) = with(binding) {
        Glide.with(itemView)
            .load(movie.image)
            .into(cover)

        title.text = movie.name
        description.text = movie.description
        fovarite.setImageDrawable(getFavoriteTogglerDrawable(movie.inFavorite))


    }

    private fun getFavoriteTogglerDrawable(inFavorite : Boolean) : Drawable? {
        return  itemView.context.getDrawable(
            if(inFavorite) R.drawable.button_like_true else R.drawable.button__like
        )
    }
}