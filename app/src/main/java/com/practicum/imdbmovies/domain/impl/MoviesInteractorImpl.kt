package com.practicum.imdbmovies.domain.impl

import com.practicum.imdbmovies.domain.api.MoviesInteractor
import com.practicum.imdbmovies.domain.api.MoviesRepository
import com.practicum.imdbmovies.domain.models.DetailsModel
import com.practicum.imdbmovies.domain.models.KinopoiskModel
import com.practicum.imdbmovies.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MoviesInteractorImpl(private val repository: MoviesRepository) : MoviesInteractor {

    override fun searchMovies(expression: String): Flow<Pair<List<KinopoiskModel>?, String?>> {
        return repository.searchMoviesRep(expression).map { result ->
            when (result) {
                is Resource.Success -> Pair(result.data, null)
                is Resource.Error -> Pair(null, result.message)
            }
        }
    }

    override fun searchMoviesForId(id: String): Flow<Pair<DetailsModel?, String?>> {
        return repository.movieDetails(id).map { result ->
            when (result) {
                is Resource.Success -> Pair(result.data, null)
                is Resource.Error -> Pair(null, result.message)
            }
        }
    }


    override fun addMovieToFavorites(movie: KinopoiskModel) {
        repository.addMovieToFavorites(movie)
    }

    override fun removeMovieFromFavorites(movie: KinopoiskModel) {
        repository.removeMovieFromFavorites(movie)
    }
}