package com.practicum.imdbmovies.domain.api

import com.practicum.imdbmovies.domain.models.DetailsModel
import com.practicum.imdbmovies.domain.models.KinopoiskModel
import com.practicum.imdbmovies.util.Resource
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {

    fun searchMoviesRep(expression : String) : Flow<Resource<List<KinopoiskModel>>>

    fun code() : Int

    fun movieDetails(id: String): Flow<Resource<DetailsModel>>

    fun addMovieToFavorites(movie: KinopoiskModel)
    fun removeMovieFromFavorites(movie: KinopoiskModel)

}