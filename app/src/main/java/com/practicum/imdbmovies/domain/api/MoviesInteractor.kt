package com.practicum.imdbmovies.domain.api

import com.practicum.imdbmovies.domain.models.KinopoiskModel
import com.practicum.imdbmovies.domain.models.MovieDetails

interface MoviesInteractor {


    fun searchMovies(expression: String, consumer: MoviesConsumer)

    interface MoviesConsumer {
        fun consume(foundMovies: List<KinopoiskModel>?, errorMessage: String?)
    }

    interface MovieDetailsConsumer {
        fun consume(detailsMovie: MovieDetails?, errorMessage: String?)
    }

    fun getMoviesDetails(movieId: String, consumer: MovieDetailsConsumer)

    fun addMovieToFavorites(movie: KinopoiskModel)
    fun removeMovieFromFavorites(movie: KinopoiskModel)
}