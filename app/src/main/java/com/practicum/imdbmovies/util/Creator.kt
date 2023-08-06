package com.practicum.imdbmovies.util

import android.app.Activity
import android.content.Context
import com.practicum.imdbmovies.MoviesAdapter
import com.practicum.imdbmovies.data.dto.MoviesRepositoryImpl
import com.practicum.imdbmovies.data.network.RetrofitNetworkClient
import com.practicum.imdbmovies.domain.api.MoviesInteractor
import com.practicum.imdbmovies.domain.api.MoviesRepository
import com.practicum.imdbmovies.domain.impl.MoviesInteractorImpl
import com.practicum.imdbmovies.presentation.MoviesSearchController
import com.practicum.imdbmovies.presentation.PosterController


object Creator {
        //для поиска без проверки на инетрнет
//    private fun getMoviesRepository(): MoviesRepository {
//        return MoviesRepositoryImpl(RetrofitNetworkClient())
//    }
//
//    fun provideMoviesInteractor(): MoviesInteractor {
//        return MoviesInteractorImpl(getMoviesRepository())
//    }

    fun provideMoviesSearchController(activity: Activity, adapter: MoviesAdapter): MoviesSearchController {
        return MoviesSearchController(activity, adapter)
    }

    fun providePosterController(activity: Activity): PosterController {
        return PosterController(activity)
    }


    private fun getMoviesRepository(context: Context): MoviesRepository {
        return MoviesRepositoryImpl(RetrofitNetworkClient(context))
    }

    fun provideMoviesInteractor(context: Context): MoviesInteractor {
        return MoviesInteractorImpl(getMoviesRepository(context))
    }
}