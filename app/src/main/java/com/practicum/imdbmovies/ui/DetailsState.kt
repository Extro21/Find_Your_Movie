package com.practicum.imdbmovies.ui

import com.practicum.imdbmovies.domain.models.DetailsModel

sealed interface DetailsState {

    data class Content(
        val movie: DetailsModel?
    ) : DetailsState

    object Error : DetailsState
}