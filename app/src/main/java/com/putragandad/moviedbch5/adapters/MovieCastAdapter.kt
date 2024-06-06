package com.putragandad.moviedbch5.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.putragandad.moviedbch5.R
import com.putragandad.moviedbch5.data.remote.details.Cast

class MovieCastAdapter(private val dataSet: List<Cast>, private val context: Context) : RecyclerView.Adapter<MovieCastAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivMovieCast : ImageView = view.findViewById(R.id.iv_movie_cast)
        val tvMovieCastName : TextView = view.findViewById(R.id.tv_movie_cast_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_cast_card, parent, false)
        return MovieCastAdapter.ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val getData = dataSet[position]

        holder.tvMovieCastName.setText(getData.name)

        val imageUrl = "https://image.tmdb.org/t/p/w500${getData.profilePath}"
        val imageView = holder.ivMovieCast

        Glide
            .with(context)
            .load(imageUrl)
            .centerCrop()
            .placeholder(R.color.placeholder_image)
            .into(imageView)
    }
}