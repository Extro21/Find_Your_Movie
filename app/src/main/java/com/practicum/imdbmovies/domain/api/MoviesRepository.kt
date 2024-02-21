package com.practicum.imdbmovies.domain.api

import com.practicum.imdbmovies.domain.models.Movie
import com.practicum.imdbmovies.domain.models.MovieDetails
import com.practicum.imdbmovies.util.Resource

interface MoviesRepository {

    fun searchMoviesRep(expression : String) : Resource<List<Movie>>

    fun code() : Int
    
    fun getMovieDetails(movieId: String): Resource<MovieDetails>

}