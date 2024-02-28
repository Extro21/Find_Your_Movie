package com.practicum.imdbmovies.data.details

import com.practicum.imdbmovies.data.search.Response

data class DetailsResponse(
    val ageRating: Int?,
    val countries: List<Country>?,
    val description: String?,
    val enName: Any?,
    val genres: List<Genre>,
    val id: Int?,
    val name: String?,
   // val persons: List<Person>,
    val rating: Rating?,
    val shortDescription: String?,
    val similarMovies: List<SimilarMovy>?,
    val videos: Videos?,
    val year: Int?,
    val persons: List<Person>?
) : Response()


data class Country(
    val name: String?
)

data class Genre(
    val name: String?
)

data class Person(
    val description: String?,
    val name: String?,
    val photo: String?,
    val enProfession: String?,
)

data class Poster(
    val previewUrl: String?,
    val url: String?
)

data class Rating(
    val filmCritics: Double?,
    val imdb: Double?,
    val kp: Double?,
)

data class SimilarMovy(
    val alternativeName: String?,
    val enName: Any?,
    val id: Int?,
    val name: String?,
    val poster: Poster?,
    val type: String?,
    val year: Int?
)

data class Videos(
    val trailers: List<Trailer>
)

data class Trailer(
    val name: String?,
    val site: String?,
    val type: String?,
    val url: String?
)