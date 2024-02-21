package com.practicum.imdbmovies.ui.poster

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.practicum.imdbmovies.util.Creator
import com.practicum.imdbmovies.R

class PosterActivity : AppCompatActivity() {
   // private lateinit var poster: ImageView

    private val posterController = Creator.providePosterPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_poster)
        posterController.onCreate()
//        poster = findViewById(R.id.poster)
//        val url = intent.extras?.getString("poster", "")
//
//        Glide.with(applicationContext)
//            .load(url)
//            .into(poster)
    }
}