package com.practicum.imdbmovies.ui.poster

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.practicum.imdbmovies.databinding.ActivityPosterBinding

class PosterActivity : AppCompatActivity() {

    private var _binding: ActivityPosterBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityPosterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        showPoster()
    }

    private fun showPoster() {
        val url = intent.extras?.getString("poster", "")

        Glide.with(applicationContext)
            .load(url)
            .into(binding.poster)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}