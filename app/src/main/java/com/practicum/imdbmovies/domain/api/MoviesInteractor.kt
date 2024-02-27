package com.practicum.imdbmovies.domain.api

import com.practicum.imdbmovies.domain.models.KinopoiskModel
import com.practicum.imdbmovies.domain.models.MovieDetails
import kotlinx.coroutines.flow.Flow

interface MoviesInteractor {


    fun searchMovies(expression: String): Flow<Pair<List<KinopoiskModel>?, String?>>

    interface MovieDetailsConsumer {
        fun consume(detailsMovie: MovieDetails?, errorMessage: String?)
    }

   // fun getMoviesDetails(movieId: String, consumer: MovieDetailsConsumer)

    fun addMovieToFavorites(movie: KinopoiskModel)
    fun removeMovieFromFavorites(movie: KinopoiskModel)
}