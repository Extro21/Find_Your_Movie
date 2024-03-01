package com.practicum.imdbmovies.ui.casts

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.practicum.imdbmovies.databinding.CastsFragmentBinding
import com.practicum.imdbmovies.domain.models.CastsModel
import com.practicum.imdbmovies.ui.casts.adapter.CastsAdapter

const val EXTRA_CASTS = "casts"

class CastsFragment : Fragment() {

    private var _binding: CastsFragmentBinding? = null
    private val binding get() = _binding!!

    private var adapter = CastsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = CastsFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val casts = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            requireArguments().getParcelableArrayList(EXTRA_CASTS, CastsModel::class.java)
        } else {
            requireArguments().getParcelableArrayList(EXTRA_CASTS)
        }

        Log.d("CastsActor", "CastsActor: $casts")

        binding.apply {
            listCasts.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            listCasts.adapter = adapter
        }

        adapter.submitList(casts)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun createArgs(
            casts: List<CastsModel>?
        ): Bundle = bundleOf(
            EXTRA_CASTS to casts
        )
    }

}