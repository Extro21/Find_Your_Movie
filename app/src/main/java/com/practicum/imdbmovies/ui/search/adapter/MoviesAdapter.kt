package com.practicum.imdbmovies.ui.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.practicum.imdbmovies.R
import com.practicum.imdbmovies.domain.models.KinopoiskModel

class MoviesAdapter(private val clickListener: MovieClickListener) :
    RecyclerView.Adapter<MovieViewHolder>() {

    private var movies = ArrayList<KinopoiskModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_movie, parent, false)
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

    fun updateData(newData: List<KinopoiskModel>) {
        val diffResult = DiffUtil.calculateDiff(DiffCallback(movies, newData))
        movies.clear()
        movies.addAll(newData)
        diffResult.dispatchUpdatesTo(this)
    }

    fun clearData(newData: List<KinopoiskModel>) {
        val diffResult = DiffUtil.calculateDiff(DiffCallback(movies, newData))
        movies.clear()
        movies.addAll(newData)
        diffResult.dispatchUpdatesTo(this)
    }

    private class DiffCallback(
        private val oldList: List<KinopoiskModel>,
        private val newList: List<KinopoiskModel>
    ) : DiffUtil.Callback() {
        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].id == newList[newItemPosition].id
        }

        override fun getOldListSize(): Int {
            return oldList.size
        }

        override fun getNewListSize(): Int {
            return newList.size
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }
    }

}