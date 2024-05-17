package com.putragandad.moviedbch5.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.putragandad.moviedbch5.R
import com.putragandad.moviedbch5.adapters.MovieCastAdapter
import com.putragandad.moviedbch5.adapters.TopRatedAdapter
import com.putragandad.moviedbch5.databinding.FragmentHomeBinding
import com.putragandad.moviedbch5.databinding.FragmentMovieDetailBinding
import com.putragandad.moviedbch5.models.details.Cast
import com.putragandad.moviedbch5.models.now_playing.NowPlayingResult
import com.putragandad.moviedbch5.ui.viewmodels.MoviesViewModel
import com.putragandad.moviedbch5.ui.viewmodels.MoviesViewModelFactory
import com.putragandad.moviedbch5.utils.Constant

class MovieDetailFragment : Fragment() {
    private var _binding: FragmentMovieDetailBinding? = null
    private val binding get() = _binding!!

    private val moviesViewModel : MoviesViewModel by viewModels {
        MoviesViewModelFactory.getInstance(requireActivity())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnBackDetail.setOnClickListener {
            findNavController().popBackStack()
        }

        val moviesId = arguments?.getInt(Constant.MOVIES_ID_EXTRA)
        if(moviesId != null) {
            moviesViewModel.getMovieDetails("$moviesId").observe(viewLifecycleOwner) { details ->
                setMovieDetails(details.title, details.releaseDate, details.overview, details.posterPath)
            }

            moviesViewModel.getMovieCredits("$moviesId").observe(viewLifecycleOwner) { credits ->
                setUpRvMovieCast(credits.cast)
            }
        }

    }

    private fun setUpRvMovieCast(dataSet: List<Cast>) {
        val adapter = MovieCastAdapter(dataSet, requireActivity())
        val recyclerView : RecyclerView? = view?.findViewById(R.id.cast_rv_container)
        recyclerView?.adapter = adapter
        recyclerView?.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
    }

    private fun setMovieDetails(title: String, releaseDate: String, synopsis: String, posterPath:String) {
        binding.tvMovieDetailTitle.setText(title)
        binding.tvMovieDetailYear.setText(releaseDate)
        binding.tvMovieDetailSynopsis.setText(synopsis)

        val imageUrl = "https://image.tmdb.org/t/p/w500$posterPath"
        Glide
            .with(requireActivity())
            .load(imageUrl)
            .centerCrop()
            .into(binding.ivMovieDetailPoster)
    }
}