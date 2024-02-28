package com.practicum.imdbmovies.common.di

import com.practicum.imdbmovies.data.storage.FavoriteStorage
import com.practicum.imdbmovies.data.network.NetworkClient
import com.practicum.imdbmovies.data.impl.MoviesRepositoryImpl
import com.practicum.imdbmovies.data.network.RetrofitNetworkClient
import com.practicum.imdbmovies.domain.api.MoviesRepository
import org.koin.dsl.module

val dataModule = module {

    single<MoviesRepository> {
        MoviesRepositoryImpl(get(), get(), get())
    }

    single<NetworkClient> {
        RetrofitNetworkClient(get())
    }

    single {
        FavoriteStorage(get())
    }

}