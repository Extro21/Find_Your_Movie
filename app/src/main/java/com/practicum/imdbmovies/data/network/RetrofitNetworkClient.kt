package com.practicum.imdbmovies.data.network


import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.practicum.imdbmovies.data.NetworkClient
import com.practicum.imdbmovies.data.dto.MoviesSearchRequest
import com.practicum.imdbmovies.data.dto.Response


import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory



class RetrofitNetworkClient() : NetworkClient {

    private val imdbBaseUrl = "https://imdb-api.com"

    private val retrofit =
        Retrofit.Builder().baseUrl(imdbBaseUrl).addConverterFactory(GsonConverterFactory.create())
            .build()


//    private fun isConnected(): Boolean {
//        val connectivityManager = context.getSystemService(
//            Context.CONNECTIVITY_SERVICE) as ConnectivityManager
//        val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
//        if (capabilities != null) {
//            when {
//                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> return true
//                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> return true
//                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> return true
//            }
//        }
//        return false
//    }

    override fun doRequest(dto: Any): Response {

        try {
            val imdbService = retrofit.create(IMDbApiService::class.java)
            if (dto is MoviesSearchRequest) {
                val resp = imdbService.findMovies(dto.expression).execute()
                val body = resp.body() ?: Response()
                return body.apply { resultCode = resp.code() }

            } else {
                return Response().apply { resultCode }
            }
        } catch (e : Exception){
            return Response().apply { resultCode }
        }

    }
}

