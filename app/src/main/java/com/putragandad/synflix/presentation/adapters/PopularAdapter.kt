package com.putragandad.synflix.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.putragandad.synflix.R
import com.putragandad.domain.models.movies.Popular

class PopularAdapter(private val dataSet: List<Popular>, private val context: Context, private val popularClickListener: PopularClickListener) : RecyclerView.Adapter<PopularAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivMovieImage: ImageView = view.findViewById(R.id.iv_popular_movie_card)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_card_popular, parent, false)
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
            popularClickListener.onClickPopularMovie(getData)
        }

        Glide
            .with(context)
            .load(imageUrl)
            .centerCrop()
            .placeholder(R.color.placeholder_image)
            .into(imageView)
    }
}

interface PopularClickListener {
    fun onClickPopularMovie(result: Popular)
}