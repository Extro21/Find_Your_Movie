package com.practicum.imdbmovies.ui.movies.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.practicum.imdbmovies.R
import com.practicum.imdbmovies.domain.models.KinopoiskModel

class MovieViewHolder(parent: ViewGroup) :
    RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context)
        .inflate(R.layout.list_item_movie, parent, false)) {

    var cover: ImageView = itemView.findViewById(R.id.cover)
    var title: TextView = itemView.findViewById(R.id.title)
    var description: TextView = itemView.findViewById(R.id.description)

    fun bind(movie: KinopoiskModel) {
        Glide.with(itemView)
            .load(movie.image)
            .into(cover)

        title.text = movie.name
        description.text = movie.description
    }
}