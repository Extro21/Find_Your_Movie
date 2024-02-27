package com.practicum.imdbmovies.data.dto

import android.util.Log
import com.practicum.imdbmovies.data.LocalStorage
import com.practicum.imdbmovies.data.NetworkClient
import com.practicum.imdbmovies.data.kinoDto.KinopoiskSearchResponse
import com.practicum.imdbmovies.domain.api.MoviesRepository
import com.practicum.imdbmovies.domain.models.KinopoiskModel
import com.practicum.imdbmovies.domain.models.MovieDetails
import com.practicum.imdbmovies.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MoviesRepositoryImpl(
    private val networkClient: NetworkClient,
    private val localStorage: LocalStorage,
) : MoviesRepository {

    var codeResp = 0

    override fun code(): Int {
        return codeResp
    }

    override fun searchMoviesRep(expression: String): Flow<Resource<List<KinopoiskModel>>> = flow {
        val response = networkClient.doRequest(MoviesSearchRequest(expression))
        Log.e("retrifitSearch", response.resultCode.toString())
        when (response.resultCode) {
            -1 -> emit(Resource.Error("Проверьте подключение к интернету"))
            200 -> {
                val stored = localStorage.getSavedFavorites()
                with(response as KinopoiskSearchResponse) {
                    val data = docs.map {
                        KinopoiskModel(
                            id = it.id,
                            image = it.poster.url,
                            it.name,
                            it.description,
                            inFavorite = stored.contains(it.id)
                        )
                    }
                    emit(Resource.Success(data = data))
                }
            }

            else -> emit(Resource.Error("Ошибка сервера"))
        }

    }

//    override fun searchMoviesRep(expression: String): Resource<List<KinopoiskModel>> {
//        val response = networkClient.doRequest(MoviesSearchRequest(expression))
//        Log.e("retrifitSearch", response.resultCode.toString())
//        return when (response.resultCode) {
//            -1 -> Resource.Error("Проверьте подключение к интернету")
//            200 -> {
//                val stored = localStorage.getSavedFavorites()
//                Log.d("retrifitSearch", (response as KinopoiskSearchResponse).docs.toString())
//                Resource.Success((response as KinopoiskSearchResponse).docs.map {
//                    KinopoiskModel(
//                        id = it.id,
//                        image = it.poster.url,
//                        it.name,
//                        it.description,
//                        inFavorite = stored.contains(it.id)
//                    )
//                })
//            }
//
//            else -> Resource.Error("Ошибка сервера")
//        }
//
//    }

    override fun addMovieToFavorites(movie: KinopoiskModel) {
        localStorage.addToFavorites(movie.id)
    }

    override fun removeMovieFromFavorites(movie: KinopoiskModel) {
        localStorage.removeFromFavorites(movie.id)
    }

//    override suspend fun getMovieDetails(movieId: String): Resource<MovieDetails> {
//        val response = networkClient.doRequest(MovieDetailsRequest(movieId))
//        return when (response.resultCode) {
//            -1 -> Resource.Error("Проверьте подключение к интернету")
//            200 ->
//                with(response as MovieDetailsResponse) {
//                    Resource.Success(
//                        MovieDetails(
//                            id, title, imDbRating, year,
//                            countries, genres, directors, writers, stars, plot
//                        )
//                    )
//                }
//
//            else -> Resource.Error("Ошибка сервера")
//        }
//
//    }



}