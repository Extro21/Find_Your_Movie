package com.practicum.imdbmovies.data.dto

import com.practicum.imdbmovies.domain.models.Movie

data class MoviesSearchResponse(val searchType: String,
                                val expression: String,
                                val results: List<MovieDto>) : Response()