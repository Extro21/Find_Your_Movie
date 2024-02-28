package com.practicum.imdbmovies.data.network


import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import com.practicum.imdbmovies.data.details.MovieDetailsRequest
import com.practicum.imdbmovies.data.search.MoviesSearchRequest
import com.practicum.imdbmovies.data.search.Response


import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitNetworkClient(private val context: Context) : NetworkClient {

    private val kinopoiskUrl = "https://api.kinopoisk.dev"


    private val retrofitKinopoisk =
        Retrofit.Builder().baseUrl(kinopoiskUrl).addConverterFactory(GsonConverterFactory.create())
            .build()
    private val kinopoiskService = retrofitKinopoisk.create(KinopoiskApi::class.java)

    //Проверка на доступность интернета на устройстве
    private fun isConnected(): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> return true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> return true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> return true
            }
        }
        return false
    }

    override suspend fun doRequest(dto: Any): Response {
        if (isConnected() == false) {
            return Response().apply { resultCode = -1 }
        }
        if ((dto !is MoviesSearchRequest) && (dto !is MovieDetailsRequest)) {
            return Response().apply { resultCode = 400 }
        }
        return try {
            when (dto) {
                is MoviesSearchRequest -> {
                    val resp = kinopoiskService.findMovies(dto.expression)
                    resp.apply { resultCode = 200 }
                }
                is MovieDetailsRequest -> {
                    val resp = kinopoiskService.findMoviesForId(dto.id)
                    resp.apply { resultCode = 200 }
                }
                else -> {
                    Response().apply { resultCode = 500 }
                }
            }
        } catch (e: Exception) {
            Log.e("Exception retrofit", e.toString() )
            Response().apply { resultCode = 700 }
        }
    }
}

