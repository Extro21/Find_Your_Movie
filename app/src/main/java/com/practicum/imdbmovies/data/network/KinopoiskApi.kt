package com.practicum.imdbmovies.data.network

import com.practicum.imdbmovies.data.details.DetailsResponse
import com.practicum.imdbmovies.data.search.KinopoiskSearchResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface KinopoiskApi {

    @Headers(TOKEN_BEARER_STRING)
    @GET("/v1.4/movie/search")
    suspend fun findMovies(@Query("query") query: String): KinopoiskSearchResponse

    @Headers(TOKEN_BEARER_STRING)
    @GET("/v1.4/movie/{id}")
    suspend fun findMoviesForId(@Path("id") id: String): DetailsResponse

    companion object {
        private const val TOKEN_BEARER_STRING = "X-API-KEY: PQDYWTH-GW84KBM-GSX1NXJ-T50GFC3"
    }
}