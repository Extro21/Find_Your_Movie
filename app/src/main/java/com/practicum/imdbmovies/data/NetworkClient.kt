package com.practicum.imdbmovies.data

import com.practicum.imdbmovies.data.dto.Response

interface NetworkClient {
    fun doRequest(dto : Any) : Response
}