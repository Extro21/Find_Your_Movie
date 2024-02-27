package com.practicum.imdbmovies.presentation.movies

import com.practicum.imdbmovies.domain.models.KinopoiskModel

sealed interface MoviesState {

    object Loading : MoviesState

    data class Content(
        val movies: List<KinopoiskModel>
    ) : MoviesState

    object Error : MoviesState

    object Empty : MoviesState

}