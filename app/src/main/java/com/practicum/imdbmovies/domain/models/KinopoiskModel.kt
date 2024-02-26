package com.practicum.imdbmovies.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class KinopoiskModel(
    val id: String,
    val image: String?,
    val name: String?,
    val description: String?
): Parcelable