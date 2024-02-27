package com.practicum.imdbmovies.domain.impl

import com.practicum.imdbmovies.domain.api.MoviesInteractor
import com.practicum.imdbmovies.domain.api.MoviesRepository
import com.practicum.imdbmovies.domain.models.KinopoiskModel
import com.practicum.imdbmovies.util.Resource
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.Flow
import java.util.concurrent.Executors

class MoviesInteractorImpl(private val repository: MoviesRepository) : MoviesInteractor {

    private val executor = Executors.newCachedThreadPool()

    override fun searchMovies(expression: String): Flow<Pair<List<KinopoiskModel>?, String?>> {
        return repository.searchMoviesRep(expression).map { result ->
            when (result) {
                is Resource.Success -> Pair(result.data, null)
                is Resource.Error -> Pair(null, result.message)
            }
        }

    }

//    override fun searchMovies(expression: String, consumer: MoviesInteractor.MoviesConsumer) {
//        executor.execute {
//            when(val resource  = repository.searchMoviesRep(expression)){
//                is Resource.Success -> consumer.consume(resource.data, null)
//                is Resource.Error -> consumer.consume(null, resource.message)
//            }
//        }
//    }


//    override fun getMoviesDetails(
//        movieId: String,
//        consumer: MoviesInteractor.MovieDetailsConsumer
//    ) {
//        executor.execute {
//            when (val resource = repository.getMovieDetails(movieId)) {
//                is Resource.Success -> {
//                    consumer.consume(resource.data, null)
//                }
//
//                is Resource.Error -> {
//                    consumer.consume(resource.data, resource.message)
//                }
//            }
//        }
//    }

    override fun addMovieToFavorites(movie: KinopoiskModel) {
        repository.addMovieToFavorites(movie)
    }

    override fun removeMovieFromFavorites(movie: KinopoiskModel) {
        repository.removeMovieFromFavorites(movie)
    }
}