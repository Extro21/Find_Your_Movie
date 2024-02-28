package com.practicum.imdbmovies.data.network

import com.practicum.imdbmovies.data.search.Response

interface NetworkClient {
    suspend fun doRequest(dto : Any) : Response
}