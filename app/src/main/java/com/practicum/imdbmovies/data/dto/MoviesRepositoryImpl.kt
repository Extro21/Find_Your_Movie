package com.practicum.imdbmovies.data.dto

import com.practicum.imdbmovies.data.NetworkClient
import com.practicum.imdbmovies.domain.api.MoviesRepository
import com.practicum.imdbmovies.domain.models.Movie

class MoviesRepositoryImpl(private val networkClient: NetworkClient) : MoviesRepository {

    override fun searchMovies(expression: String): List<Movie> {
      val response = networkClient.doRequest(MoviesSearchRequest(expression))
        if(response.resultCode == 200){
            return (response as MoviesSearchResponse).results.map {
                Movie(it.id, it.resultType, it.image, it.title, it.description)
            }
        } else {
            return emptyList()
        }


    }


}