package com.practicum.imdbmovies.data.storage

import android.content.Context
import android.content.SharedPreferences

class FavoriteStorage(context: Context) {

    private val sharedPref: SharedPreferences = context.getSharedPreferences("local_storage", Context.MODE_PRIVATE)

    private companion object {
        const val FAVORITES_KEY = "FAVORITES_KEY"
    }

    fun addToFavorites(movieId: String) {
        changeFavorites(movieId = movieId, remove = false)
    }

    fun removeFromFavorites(movieId: String) {
        changeFavorites(movieId = movieId, remove = true)
    }

    fun getSavedFavorites(): Set<String> {
        return sharedPref.getStringSet(FAVORITES_KEY, emptySet()) ?: emptySet()
    }

    private fun changeFavorites(movieId: String, remove: Boolean) {
        val mutableSet = getSavedFavorites().toMutableSet()
        val modified = if (remove) mutableSet.remove(movieId) else mutableSet.add(movieId)
        if (modified)
            sharedPref.edit().putStringSet(FAVORITES_KEY, mutableSet).apply()
    }

}