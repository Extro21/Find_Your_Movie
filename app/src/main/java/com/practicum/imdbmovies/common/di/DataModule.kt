package com.practicum.imdbmovies.common.di

import com.practicum.imdbmovies.data.LocalStorage
import com.practicum.imdbmovies.data.NetworkClient
import com.practicum.imdbmovies.data.dto.MoviesRepositoryImpl
import com.practicum.imdbmovies.data.network.RetrofitNetworkClient
import com.practicum.imdbmovies.domain.api.MoviesRepository
import org.koin.dsl.module
import kotlin.math.sin

val dataModule = module {

    single<MoviesRepository> {
        MoviesRepositoryImpl(get(), get())
    }

    single<NetworkClient> {
        RetrofitNetworkClient(get())
    }

    single {
        LocalStorage(get())
    }

}