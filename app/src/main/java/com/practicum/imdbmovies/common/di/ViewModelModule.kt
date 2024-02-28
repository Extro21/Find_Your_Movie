package com.practicum.imdbmovies.common.di

import com.practicum.imdbmovies.ui.details.DetailsViewModel
import com.practicum.imdbmovies.ui.search.MoviesSearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModule = module {

    viewModel {
        MoviesSearchViewModel(get())
    }

    viewModel {
        DetailsViewModel(get())
    }

}