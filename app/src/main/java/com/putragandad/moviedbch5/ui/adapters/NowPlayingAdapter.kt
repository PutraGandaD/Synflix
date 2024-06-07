package com.putragandad.moviedbch5.ui.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.putragandad.moviedbch5.R
import com.putragandad.moviedbch5.data.remote.response.now_playing.NowPlayingResult

class NowPlayingAdapter(private val dataSet: List<NowPlayingResult>, private val context: Context, private val nowPlayingClickListener: NowPlayingClickListener) : RecyclerView.Adapter<NowPlayingAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivMovieImage : ImageView = view.findViewById(R.id.iv_nowplaying_movie_card)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_card_nowplaying, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val getData = dataSet[position]

        val imageUrl = "https://image.tmdb.org/t/p/w500${getData.posterPath}"
        val imageView = holder.ivMovieImage

        imageView.setOnClickListener {
            nowPlayingClickListener.onClickMovieNowPlaying(getData)
        }

        Glide
            .with(context)
            .load(imageUrl)
            .centerCrop()
            .placeholder(R.color.placeholder_image)
            .into(imageView)
//        Log.d("Poster Path", "${getData.id}")
    }
}

interface NowPlayingClickListener {
    fun onClickMovieNowPlaying(result: NowPlayingResult)
}