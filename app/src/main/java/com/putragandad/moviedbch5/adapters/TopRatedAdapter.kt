package com.putragandad.moviedbch5.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.putragandad.moviedbch5.R
import com.putragandad.moviedbch5.models.top_rated.TopRatedResult

class TopRatedAdapter(private val dataSet: List<TopRatedResult>, private val context: Context, private val topRatedClickListener: TopRatedClickListener) : RecyclerView.Adapter<TopRatedAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivMovieImage : ImageView = view.findViewById(R.id.iv_toprated_movie)
        val tvMovieTitle : TextView = view.findViewById(R.id.tv_movie_popular_title)
        val tvMovieRating : TextView = view.findViewById(R.id.tv_movie_popular_rating)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_card_toprated, parent, false)
        return TopRatedAdapter.ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val getData = dataSet[position]

        holder.tvMovieTitle.setText(getData.title)
        holder.tvMovieRating.setText("Voted by User Avg : ${getData.voteAverage}")

        val imageUrl = "https://image.tmdb.org/t/p/w500${getData.backdropPath}"
        val imageView = holder.ivMovieImage

        holder.itemView.setOnClickListener {
            topRatedClickListener.onClickMovieTopRated(getData)
        }

        Glide
            .with(context)
            .load(imageUrl)
            .apply(RequestOptions().override(500, 200))
            .centerCrop()
            .placeholder(R.color.placeholder_image)
            .into(imageView)
    }
}

interface TopRatedClickListener {
    fun onClickMovieTopRated(result: TopRatedResult)
}