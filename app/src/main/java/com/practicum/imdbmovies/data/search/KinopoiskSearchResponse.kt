package com.practicum.imdbmovies.data.search

data class KinopoiskSearchResponse(
    val docs: List<Doc>,
) : Response()

data class Doc(
    val ageRating: Int?,
    val alternativeName: String?,
    val backdrop: Backdrop,
    val countries: List<Country>,
    val description: String?,
    val enName: String?,
    val externalId: ExternalId,
    val genres: List<Genre>,
    val id: String,
    val isSeries: Boolean,
    val logo: Logo,
    val movieLength: Int?,
    val name: String?,
    val names: List<Name>,
    val poster: Poster,
    val rating: Rating,
    val ratingMpaa: String?,
    val releaseYears: List<Any>,
    val seriesLength: Any?,
    val shortDescription: String?,
    val status: Any?,
    val ticketsOnSale: Boolean,
    val top10: Any?,
    val top250: Int?,
    val totalSeriesLength: Any?,
    val type: String?,
    val typeNumber: Int?,
    val votes: Votes,
    val year: Int?
)

data class Backdrop(
    val previewUrl: String?,
    val url: String?
)

data class Country(
    val name: String?
)

data class ExternalId(
    val imdb: String?,
    val kpHD: String?,
    val tmdb: Int?
)

data class Genre(
    val name: String?
)

data class Logo(
    val previewUrl: Any?,
    val url: String?
)

data class Name(
    val language: String?,
    val name: String?,
    val type: String?
)

data class Poster(
    val previewUrl: String?,
    val url: String?
)

data class Rating(
    val await: Int?,
    val filmCritics: Double?,
    val imdb: Double?,
    val kp: Double?,
    val russianFilmCritics: Double?
)

data class Votes(
    val await: Int?,
    val filmCritics: Int?,
    val imdb: Int?,
    val kp: Int?,
    val russianFilmCritics: Int?
)
