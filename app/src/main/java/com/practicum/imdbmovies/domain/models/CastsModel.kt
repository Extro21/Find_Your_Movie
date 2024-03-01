package com.practicum.imdbmovies.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CastsModel (
    val id : String,
    val photo: String?,
    val name : String?,
    val description : String?,
    val profession : String?,
) : Parcelable