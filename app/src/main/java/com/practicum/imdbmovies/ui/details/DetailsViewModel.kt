package com.practicum.imdbmovies.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.practicum.imdbmovies.domain.api.MoviesInteractor
import com.practicum.imdbmovies.domain.models.DetailsModel
import com.practicum.imdbmovies.ui.DetailsState
import kotlinx.coroutines.launch

class DetailsViewModel(private val interactor: MoviesInteractor) : ViewModel() {

    private val stateLiveData = MutableLiveData<DetailsState>()
    fun observeState(): LiveData<DetailsState> = stateLiveData

    fun searchRequest(id: String) {
            viewModelScope.launch {
                interactor.searchMoviesForId(id)
                    .collect { pair ->
                        processResult(pair.first, pair.second)
                    }
            }
    }

    private fun processResult(foundMovie: DetailsModel?, errorMessage: String?) {
        when {
            errorMessage != null -> {
                renderState(DetailsState.Error)
            }

            else -> {
                renderState(
                    DetailsState.Content(
                        foundMovie
                    )
                )
            }
        }
    }

    private fun renderState(state: DetailsState) {
        stateLiveData.postValue(state)
    }


}