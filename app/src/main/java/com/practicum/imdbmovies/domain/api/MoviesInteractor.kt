package com.practicum.imdbmovies.domain.api

import com.practicum.imdbmovies.domain.models.Movie
import com.practicum.imdbmovies.domain.models.MovieDetails

interface MoviesInteractor {


    fun searchMovies(expression: String, consumer: MoviesConsumer)

    interface MoviesConsumer {
        fun consume(foundMovies: List<Movie>?, errorMessage : String?)
    }

    interface MovieDetailsConsumer {
        fun consume(detailsMovie: MovieDetails?, errorMessage: String?)
    }

    fun getMoviesDetails(movieId: String, consumer: MovieDetailsConsumer)
}