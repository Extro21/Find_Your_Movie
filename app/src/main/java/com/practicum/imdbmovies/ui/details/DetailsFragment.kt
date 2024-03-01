package com.practicum.imdbmovies.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.practicum.imdbmovies.R
import com.practicum.imdbmovies.databinding.DetailsFragmentBinding
import com.practicum.imdbmovies.domain.models.DetailsModel
import com.practicum.imdbmovies.ui.DetailsState
import com.practicum.imdbmovies.ui.ERROR_ID
import com.practicum.imdbmovies.ui.casts.CastsFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

const val EXTRA_ID = "id_movies"

class DetailsFragment : Fragment() {

    private var _binding: DetailsFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModel<DetailsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DetailsFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val idMovie = requireArguments().getString(EXTRA_ID)
        if(idMovie == ERROR_ID) {

        } else {
            idMovie?.let { viewModel.searchRequest(it) }

            viewModel.observeState().observe(viewLifecycleOwner) { state ->
                when (state) {
                    is DetailsState.Content -> showContent(state.movie)
                    is DetailsState.Error -> binding.errorMessage.text =
                        getString(R.string.nothing_internet)
                }
            }
        }


    }

    private fun showContent(movie: DetailsModel?) = with(binding) {
        ratingValue.text = movie?.rating.toString()
        yearValue.text = movie?.year.toString()
        countryValue.text = movie?.countries
        genreValue.text = movie?.genres
        directorValue.text = movie?.director
        castValue.text = movie?.cast
        plot.text = movie?.description
        writerValue.text = movie?.writer
        title.text = movie?.name

        binding.showCastButton.setOnClickListener {
            findNavController().navigate(R.id.action_posterDetailsFragment_to_castsFragment,
                CastsFragment.createArgs(movie?.persons))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        fun newInstance(id: String) =
            DetailsFragment().apply {
                arguments = Bundle().apply {
                    putString(EXTRA_ID, id)
                }
            }
    }

}