package com.practicum.imdbmovies.ui.poster

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.practicum.imdbmovies.databinding.PosterFragmentBinding

const val EXTRA_IMAGE = "image_poster"
class PosterFragment : Fragment() {

    private var _binding: PosterFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = PosterFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showPoster()
    }

    private fun showPoster() {
        val url = requireArguments().getString(EXTRA_IMAGE)
        if(url == EXTRA_IMAGE) {
            binding.placeholderMessageNotInternet.isVisible = true
        } else {
            binding.placeholderMessageNotInternet.isVisible = false
            Glide.with(requireContext())
                .load(url)
                .into(binding.poster)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(url: String) =
            PosterFragment().apply {
                arguments = Bundle().apply {
                    putString(EXTRA_IMAGE, url)
                }
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}