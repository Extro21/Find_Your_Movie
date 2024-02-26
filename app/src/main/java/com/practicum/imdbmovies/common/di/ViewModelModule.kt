package com.practicum.imdbmovies.common.di

import com.practicum.imdbmovies.presentation.movies.MoviesSearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModule = module {

    viewModel {
        MoviesSearchViewModel(get())
    }

}