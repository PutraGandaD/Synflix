package com.putragandad.moviedbch5.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.putragandad.moviedbch5.R
import com.putragandad.moviedbch5.presentation.adapters.MovieCastAdapter
import com.putragandad.moviedbch5.databinding.FragmentMovieDetailBinding
import com.putragandad.moviedbch5.domain.models.movies.MovieCast
import com.putragandad.moviedbch5.presentation.viewmodels.MoviesViewModel
import com.putragandad.moviedbch5.utils.Constant
import com.putragandad.moviedbch5.utils.Resource
import org.koin.android.ext.android.inject

class MovieDetailFragment : Fragment() {
    private var _binding: FragmentMovieDetailBinding? = null
    private val binding get() = _binding!!

    private val moviesViewModel : MoviesViewModel by inject()

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
            moviesViewModel.getMovieDetails("$moviesId")
            moviesViewModel.movieDetails.observe(viewLifecycleOwner) { details ->
                when(details) {
                    is Resource.Success -> {
                        details.data?.let { movieDetails ->
                            setMovieDetails(movieDetails.title, movieDetails.releaseDate, movieDetails.overview, movieDetails.posterPath)
                        }
                    }
                    is Resource.Loading -> {

                    }
                    is Resource.Error -> {

                    }
                }
            }

            moviesViewModel.getMovieCast("$moviesId")
            moviesViewModel.movieCast.observe(viewLifecycleOwner) { cast ->
                when(cast) {
                    is Resource.Success -> {
                        cast.data?.let {
                            setUpRvMovieCast(it)
                        }
                        //Toast.makeText(requireActivity(), "SUCCESS", Toast.LENGTH_SHORT).show()
                    }
                    is Resource.Loading -> {
                        //Toast.makeText(requireActivity(), "LOADING", Toast.LENGTH_SHORT).show()
                    }
                    is Resource.Error -> {
                        //Toast.makeText(requireActivity(), "ERROR", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

    }

    private fun setUpRvMovieCast(dataSet: List<MovieCast>) {
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