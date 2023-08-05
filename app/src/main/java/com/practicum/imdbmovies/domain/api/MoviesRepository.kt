package com.practicum.imdbmovies.domain.api

import com.practicum.imdbmovies.domain.models.Movie

interface MoviesRepository {

    fun searchMoviesRep(expression : String) : List<Movie>

    fun code() : Int

}