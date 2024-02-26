package com.practicum.imdbmovies.data.dto

import android.util.Log
import com.practicum.imdbmovies.data.NetworkClient
import com.practicum.imdbmovies.data.kinoDto.KinopoiskSearchResponse
import com.practicum.imdbmovies.domain.api.MoviesRepository
import com.practicum.imdbmovies.domain.models.KinopoiskModel
import com.practicum.imdbmovies.domain.models.MovieDetails
import com.practicum.imdbmovies.util.Resource

class MoviesRepositoryImpl(private val networkClient: NetworkClient) : MoviesRepository {

    var codeResp = 0

    override fun code(): Int {
        return codeResp
    }

    override fun searchMoviesRep(expression: String): Resource<List<KinopoiskModel>> {
        val response = networkClient.doRequest(MoviesSearchRequest(expression))
        Log.e("retrifitSearch", response.resultCode.toString() )
        return when (response.resultCode) {
            -1 -> Resource.Error("Проверьте подключение к интернету")
            200 -> {
                Log.d("retrifitSearch", (response as KinopoiskSearchResponse).docs.toString())
                Resource.Success((response as KinopoiskSearchResponse).docs.map {
                    KinopoiskModel(id = it.id, image = it.poster.url, it.name, it.description)
                })
            }
            else -> Resource.Error("Ошибка сервера")
        }

    }

//    override fun searchMoviesRep(expression: String): Resource<List<Movie>> {
//        val response = networkClient.doRequest(MoviesSearchRequest(expression))
//        return when (response.resultCode) {
//            -1 -> Resource.Error("Проверьте подключение к интернету")
//            200 -> {
//                Resource.Success((response as MoviesSearchResponse).results.map {
//                    Movie(it.id, it.resultType, it.image, it.title, it.description)
//                })
//            }
//            else -> Resource.Error("Ошибка сервера")
//        }
//
//    }



    override fun getMovieDetails(movieId: String): Resource<MovieDetails> {
        val response = networkClient.doRequest(MovieDetailsRequest(movieId))
        return when (response.resultCode) {
            -1 -> Resource.Error("Проверьте подключение к интернету")
            200 ->
                with(response as MovieDetailsResponse) {
                Resource.Success(MovieDetails(id, title, imDbRating, year,
                    countries, genres, directors, writers, stars, plot))
            }
            else -> Resource.Error("Ошибка сервера")
        }

    }


    //Без использования класса Resource
//    override fun searchMoviesRep(expression: String): List<Movie> {
//        val response = networkClient.doRequest(MoviesSearchRequest(expression))
//        codeResp = response.resultCode
//        if(codeResp == 200){
//            return (response as MoviesSearchResponse).results.map {
//                Movie(it.id, it.resultType, it.image, it.title, it.description)
//            }
//        } else {
//            return emptyList()
//        }
//
//
//    }


}