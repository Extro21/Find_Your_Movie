package com.practicum.imdbmovies.domain.api

import com.practicum.imdbmovies.domain.models.DetailsModel
import com.practicum.imdbmovies.domain.models.KinopoiskModel
import kotlinx.coroutines.flow.Flow

interface MoviesInteractor {

    fun searchMovies(expression: String): Flow<Pair<List<KinopoiskModel>?, String?>>

    fun searchMoviesForId(id: String): Flow<Pair<DetailsModel?, String?>>


    fun addMovieToFavorites(movie: KinopoiskModel)
    fun removeMovieFromFavorites(movie: KinopoiskModel)
}