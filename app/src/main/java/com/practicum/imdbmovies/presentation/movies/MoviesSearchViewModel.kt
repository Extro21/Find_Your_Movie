package com.practicum.imdbmovies.presentation.movies

import android.os.Handler
import android.os.Looper
import android.os.SystemClock
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.practicum.imdbmovies.domain.api.MoviesInteractor
import com.practicum.imdbmovies.domain.models.KinopoiskModel

class MoviesSearchViewModel(
    private val moviesInteractor: MoviesInteractor
) : ViewModel() {

    companion object {
        private const val SEARCH_DEBOUNCE_DELAY = 2000L
        private val SEARCH_REQUEST_TOKEN = Any()
    }

    private val handler = Handler(Looper.getMainLooper())

    private val stateLiveData = MutableLiveData<MoviesState>()
    fun observeState(): LiveData<MoviesState> = stateLiveData

    private var latestSearchText: String? = null

    override fun onCleared() {
        handler.removeCallbacksAndMessages(SEARCH_REQUEST_TOKEN)
    }

    fun searchDebounce(changedText: String) {
        if (latestSearchText == changedText) {
            return
        }

        this.latestSearchText = changedText
        handler.removeCallbacksAndMessages(SEARCH_REQUEST_TOKEN)

        val searchRunnable = Runnable { searchRequest(changedText) }

        val postTime = SystemClock.uptimeMillis() + SEARCH_DEBOUNCE_DELAY
        handler.postAtTime(
            searchRunnable,
            SEARCH_REQUEST_TOKEN,
            postTime,
        )
    }

    private fun searchRequest(newSearchText: String) {
        if (newSearchText.isNotEmpty()) {
            renderState(MoviesState.Loading)

            moviesInteractor.searchMovies(newSearchText, object : MoviesInteractor.MoviesConsumer {
                override fun consume(foundMovies: List<KinopoiskModel>?, errorMessage: String?) {
                    val movies = mutableListOf<KinopoiskModel>()
                    if (foundMovies != null) {
                        val moviesFilter = foundMovies.filter {
                            it.image != null && it.name?.isNotEmpty() == true
                        }
                        movies.addAll(moviesFilter)
                        Log.d("listMovie", movies.toString())
                    }

                    when {
                        errorMessage != null -> {
                            Log.d("retrifitSearch", errorMessage)
                            renderState(
                                MoviesState.Error("Исправить текст")
                            )
                        }

                        movies.isEmpty() -> {
                            renderState(
                                MoviesState.Empty("Исправить текст")
                            )
                        }

                        else -> {
                            renderState(
                                MoviesState.Content(
                                    movies = movies,
                                )
                            )
                        }
                    }

                }
            })
        }
    }

    private fun renderState(state: MoviesState) {
        stateLiveData.postValue(state)
    }
}
