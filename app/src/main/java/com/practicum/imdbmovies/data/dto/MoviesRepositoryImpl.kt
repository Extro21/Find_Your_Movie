package com.practicum.imdbmovies.data.dto

import com.practicum.imdbmovies.data.NetworkClient
import com.practicum.imdbmovies.domain.api.MoviesRepository
import com.practicum.imdbmovies.domain.models.Movie

class MoviesRepositoryImpl(private val networkClient: NetworkClient) : MoviesRepository {

    var codeResp = 0

    override fun code(): Int {
        return codeResp
    }

    override fun searchMoviesRep(expression: String): List<Movie> {
      val response = networkClient.doRequest(MoviesSearchRequest(expression))
        codeResp = response.resultCode
        if(codeResp == 200){
            return (response as MoviesSearchResponse).results.map {
                Movie(it.id, it.resultType, it.image, it.title, it.description)
            }
        } else {
            return emptyList()
        }


    }


}