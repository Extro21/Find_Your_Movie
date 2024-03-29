package com.practicum.imdbmovies.ui.search

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.practicum.imdbmovies.R
import com.practicum.imdbmovies.databinding.FragmentMoviesBinding
import com.practicum.imdbmovies.domain.models.KinopoiskModel
import com.practicum.imdbmovies.ui.MoviesState
import com.practicum.imdbmovies.ui.PosterDetailsFragment
import com.practicum.imdbmovies.ui.search.adapter.MoviesAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class MoviesFragment : Fragment() {

    private var _binding: FragmentMoviesBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModel<MoviesSearchViewModel>()

    companion object {
        private const val CLICK_DEBOUNCE_DELAY = 1000L
    }

    private val adapter = MoviesAdapter(
        object  : MoviesAdapter.MovieClickListener {
            override fun onMovieClick(movie: KinopoiskModel) {
                if (clickDebounce()) {
                    findNavController().navigate(R.id.action_moviesFragment_to_posterDetailsFragment,
                        PosterDetailsFragment.createArgs(movie.image, movie.id))
                }
            }

            override fun onFavoriteToggleClick(movie: KinopoiskModel) {
                viewModel.toggleFavorite(movie)
            }
        }
    )

    private lateinit var textWatcher: TextWatcher
    private var isClickAllowed = true
    private val handler = Handler(Looper.getMainLooper())


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoviesBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.moviesList.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
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

        viewModel.observeState().observe(viewLifecycleOwner) {
            render(it)
        }

    }

    private fun render(state: MoviesState) {
        when (state) {
            is MoviesState.Content -> showContent(state.movies)
            is MoviesState.Empty -> showEmpty()
            is MoviesState.Error -> showError()
            is MoviesState.Loading -> showLoading()
        }
    }

    private fun showLoading() = with(binding) {
        moviesList.visibility = View.GONE
        placeholderMessage.visibility = View.GONE
        progressBar.visibility = View.VISIBLE
    }

    private fun showError() = with(binding) {
        moviesList.visibility = View.GONE
        placeholderMessage.visibility = View.VISIBLE
        progressBar.visibility = View.GONE
        placeholderMessage.text = getString(R.string.nothing_internet)
    }

    private fun showEmpty() = with(binding) {
        moviesList.visibility = View.GONE
        placeholderMessage.visibility = View.VISIBLE
        progressBar.visibility = View.GONE
        placeholderMessage.text = getString(R.string.nothing_found)
    }

    private fun showContent(movies: List<KinopoiskModel>) = with(binding) {
        moviesList.visibility = View.VISIBLE
        placeholderMessage.visibility = View.GONE
        progressBar.visibility = View.GONE
       // adapter.movies.clear()
        adapter.updateData(movies)
    }

    private fun clickDebounce(): Boolean {
        val current = isClickAllowed
        if (isClickAllowed) {
            isClickAllowed = false
            handler.postDelayed({ isClickAllowed = true }, CLICK_DEBOUNCE_DELAY)
        }
        return current
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}