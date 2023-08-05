package com.practicum.imdbmovies.domain.api

import com.practicum.imdbmovies.domain.models.Movie

interface MoviesRepository {

    fun searchMovies(expression : String) : List<Movie>

}