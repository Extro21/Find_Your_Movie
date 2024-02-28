package com.practicum.imdbmovies.domain.models

data class DetailsModel (
    val ageRating: Int?,
    val countries: String?,
    val description: String?,
    val enName: String?,
    val genres: String?,
    val id: Int?,
    val name: String?,
    //val persons: List<String>,
    val rating: Double?,
    val shortDescription: String?,
   // val similarMovies: List<SimilarMovy>,
    val videos: String?,
    val year: Int?,
    val director: String?,
    val cast : String?,
    val writer: String?,
)

data class Rating(
    val filmCritics: Double?,
    val imdb: Double?,
    val kp: Double?,
)

