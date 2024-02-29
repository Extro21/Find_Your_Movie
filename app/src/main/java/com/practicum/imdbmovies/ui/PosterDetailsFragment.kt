package com.practicum.imdbmovies.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.practicum.imdbmovies.databinding.PosterDeteilsFragmentBinding
import com.practicum.imdbmovies.ui.details.EXTRA_ID
import com.practicum.imdbmovies.ui.poster.EXTRA_IMAGE

const val ERROR_URL = "error_url"
const val ERROR_ID = "error_id"
class PosterDetailsFragment : Fragment() {

    private var _binding: PosterDeteilsFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var tabMediator: TabLayoutMediator

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = PosterDeteilsFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        hideKeyboard()

        val url = requireArguments().getString(EXTRA_IMAGE) ?: ERROR_URL
        val id = requireArguments().getString(EXTRA_ID) ?: EXTRA_ID

        binding.apply {
            viewPageInfo.adapter = InfoPagerAdapter(this@PosterDetailsFragment, url, id)

            tabMediator = TabLayoutMediator(infoMenu, viewPageInfo) { tab, position ->
                when (position) {
                    0 -> tab.text = "Постер"
                    1 -> tab.text = "О фильме"
                }
            }
            tabMediator.attach()
        }



    }

    private fun hideKeyboard() {
        val inputMethodManager = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(requireView().windowToken, 0)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun createArgs(
            image : String?,
            id : String?
        ): Bundle = bundleOf(
            EXTRA_IMAGE to image,
            EXTRA_ID to id

        )
    }

}