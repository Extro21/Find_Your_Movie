package com.practicum.imdbmovies.ui.movies

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.practicum.imdbmovies.ui.movies.adapter.MoviesAdapter
import com.practicum.imdbmovies.databinding.ActivityMoviesBinding
import com.practicum.imdbmovies.domain.models.KinopoiskModel
import com.practicum.imdbmovies.presentation.movies.MoviesSearchViewModel
import com.practicum.imdbmovies.presentation.movies.MoviesState
import com.practicum.imdbmovies.ui.poster.PosterActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MoviesActivity : ComponentActivity() {

    private var _binding: ActivityMoviesBinding? = null
    private val binding get() = _binding!!

    companion object {
        private const val CLICK_DEBOUNCE_DELAY = 1000L
    }

    private val adapter = MoviesAdapter {
        if (clickDebounce()) {
            val intent = Intent(this, PosterActivity::class.java)
            intent.putExtra("poster", it.image)
            startActivity(intent)
        }
    }

    private lateinit var textWatcher: TextWatcher
    private var isClickAllowed = true
    private val handler = Handler(Looper.getMainLooper())
    private val viewModel by viewModel<MoviesSearchViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMoviesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.moviesList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.moviesList.adapter = adapter

        textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.searchDebounce(
                    changedText = s?.toString() ?: ""
                )
            }

            override fun afterTextChanged(s: Editable?) {
            }
        }
        textWatcher.let { binding.queryInput.addTextChangedListener(it) }

        viewModel.observeState().observe(this) {
            render(it)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        textWatcher.let { binding.queryInput.removeTextChangedListener(it) }
    }

    private fun render(state: MoviesState) {
        when (state) {
            is MoviesState.Content -> showContent(state.movies)
            is MoviesState.Empty -> showEmpty(state.message)
            is MoviesState.Error -> showError(state.errorMessage)
            is MoviesState.Loading -> showLoading()
        }
    }

    private fun showLoading() = with(binding) {
        moviesList.visibility = View.GONE
        placeholderMessage.visibility = View.GONE
        progressBar.visibility = View.VISIBLE
    }

    private fun showError(errorMessage: String) = with(binding) {
        moviesList.visibility = View.GONE
        placeholderMessage.visibility = View.VISIBLE
        progressBar.visibility = View.GONE
        placeholderMessage.text = errorMessage
    }

    private fun showEmpty(emptyMessage: String) {
        showError(emptyMessage)
    }

    private fun showContent(movies: List<KinopoiskModel>) = with(binding) {
        moviesList.visibility = View.VISIBLE
        placeholderMessage.visibility = View.GONE
        progressBar.visibility = View.GONE
        adapter.movies.clear()
        adapter.movies.addAll(movies)
        adapter.notifyDataSetChanged()
    }

    private fun clickDebounce(): Boolean {
        val current = isClickAllowed
        if (isClickAllowed) {
            isClickAllowed = false
            handler.postDelayed({ isClickAllowed = true }, CLICK_DEBOUNCE_DELAY)
        }
        return current
    }

}
