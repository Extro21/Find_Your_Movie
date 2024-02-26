package com.practicum.imdbmovies.common.di

import com.practicum.imdbmovies.domain.api.MoviesInteractor
import com.practicum.imdbmovies.domain.impl.MoviesInteractorImpl
import org.koin.dsl.module


val domainModule = module {

    factory<MoviesInteractor> {
        MoviesInteractorImpl(get())
    }

}