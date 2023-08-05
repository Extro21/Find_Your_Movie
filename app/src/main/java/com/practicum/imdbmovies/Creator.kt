package com.practicum.imdbmovies

import android.content.Context
import com.practicum.imdbmovies.data.dto.MoviesRepositoryImpl
import com.practicum.imdbmovies.data.network.RetrofitNetworkClient
import com.practicum.imdbmovies.domain.api.MoviesInteractor
import com.practicum.imdbmovies.domain.api.MoviesRepository
import com.practicum.imdbmovies.domain.impl.MoviesInteractorImpl


object Creator {

    private fun getMoviesRepository(): MoviesRepository {
        return MoviesRepositoryImpl(RetrofitNetworkClient())
    }

    fun provideMoviesInteractor(): MoviesInteractor {
        return MoviesInteractorImpl(getMoviesRepository())
    }
}