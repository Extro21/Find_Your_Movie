package com.practicum.imdbmovies.data.impl

import android.content.Context
import com.practicum.imdbmovies.R
import com.practicum.imdbmovies.data.storage.FavoriteStorage
import com.practicum.imdbmovies.data.network.NetworkClient
import com.practicum.imdbmovies.data.details.DetailsResponse
import com.practicum.imdbmovies.data.details.Person
import com.practicum.imdbmovies.data.search.KinopoiskSearchResponse
import com.practicum.imdbmovies.data.details.MovieDetailsRequest
import com.practicum.imdbmovies.data.search.MoviesSearchRequest
import com.practicum.imdbmovies.domain.api.MoviesRepository
import com.practicum.imdbmovies.domain.models.DetailsModel
import com.practicum.imdbmovies.domain.models.KinopoiskModel
import com.practicum.imdbmovies.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

const val DIRECTOR = "director"
const val ACTOR = "actor"
const val WRITER = "writer"

class MoviesRepositoryImpl(
    private val networkClient: NetworkClient,
    private val favoriteStorage: FavoriteStorage,
    private val context: Context,
) : MoviesRepository {

    var codeResp = 0

    override fun code(): Int {
        return codeResp
    }

    override fun searchMoviesRep(expression: String): Flow<Resource<List<KinopoiskModel>>> = flow {
        val response = networkClient.doRequest(MoviesSearchRequest(expression))
        when (response.resultCode) {
            -1 -> emit(Resource.Error(context.getString(R.string.nothing_internet)))
            200 -> {
                val stored = favoriteStorage.getSavedFavorites()
                with(response as KinopoiskSearchResponse) {
                    val data = docs.map {
                        KinopoiskModel(
                            id = it.id,
                            image = it.poster.url,
                            it.name,
                            it.description,
                            it.rating.imdb.toString(),
                            inFavorite = stored.contains(it.id)
                        )
                    }
                    emit(Resource.Success(data = data))
                }
            }

            else -> emit(Resource.Error(context.getString(R.string.nothing_found)))
        }
    }

    override fun movieDetails(id: String): Flow<Resource<DetailsModel>> = flow {
        val response = networkClient.doRequest(MovieDetailsRequest(id))
        when (response.resultCode) {
            -1 -> emit(Resource.Error(context.getString(R.string.nothing_internet)))
            200 -> {
                with(response as DetailsResponse) {
                    val data = DetailsModel(
                        ageRating = ageRating,
                        countries = countries?.joinToString(separator = ", ") { it.name.toString() },
                        description = description,
                        enName = enName.toString(),
                        id = id.toInt(),
                        name = name,
                        rating = rating?.imdb,
                        shortDescription = shortDescription,
                        videos = videos.toString(),
                        year = year,
                        genres = genres.joinToString(separator = ", ") { it.name.toString() },
                        director = mapCastsListToString(persons, DIRECTOR),
                        cast = mapCastsListToString(persons, ACTOR),
                        writer = mapCastsListToString(persons, WRITER)
                    )
                    emit(Resource.Success(data = data))
                }
            }

            else -> emit(Resource.Error(context.getString(R.string.nothing_found)))
        }
    }

    private fun mapCastsListToString(persons: List<Person>?, value: String): String? {
        return persons?.filter { it.enProfession == value }
            ?.map { it.name }
            ?.joinToString(separator = ", ")
    }

    override fun addMovieToFavorites(movie: KinopoiskModel) {
        favoriteStorage.addToFavorites(movie.id)
    }

    override fun removeMovieFromFavorites(movie: KinopoiskModel) {
        favoriteStorage.removeFromFavorites(movie.id)
    }


}