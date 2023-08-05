package com.practicum.imdbmovies.domain.impl

import com.practicum.imdbmovies.domain.api.MoviesInteractor
import com.practicum.imdbmovies.domain.api.MoviesRepository
import java.util.concurrent.Executors

class MoviesInteractorImpl(private val repository: MoviesRepository) : MoviesInteractor {

    private val executor = Executors.newCachedThreadPool()


    override fun searchMovies(expression: String, consumer: MoviesInteractor.MoviesConsumer) {

        executor.execute {
            consumer.consume(repository.searchMoviesRep(expression), repository.code())
        }

    }
}