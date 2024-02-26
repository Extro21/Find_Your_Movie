package com.practicum.imdbmovies.data.network

import com.practicum.imdbmovies.data.kinoDto.KinopoiskSearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface KinopoiskApi {

//    @GET("/en/API/SearchMovie/k_zcuw1ytf/{expression}")
//    fun findMovies(@Path("expression") expression: String): Call<MoviesSearchResponse>


    @Headers(TOKEN_BEARER_STRING)
    @GET("/v1.4/movie/search")
    fun findMovies(@Query("query") query: String): Call<KinopoiskSearchResponse>

    companion object {
        private const val TOKEN_BEARER_STRING = "X-API-KEY: PQDYWTH-GW84KBM-GSX1NXJ-T50GFC3"
    }
}