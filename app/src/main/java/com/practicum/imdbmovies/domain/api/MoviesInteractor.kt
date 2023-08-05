package com.practicum.imdbmovies.domain.api

import com.practicum.imdbmovies.domain.models.Movie

interface MoviesInteractor {


    fun searchMovies(expression: String, consumer: MoviesConsumer)

    interface MoviesConsumer {
        fun consume(foundMovies: List<Movie>, code : Int)
    }


}