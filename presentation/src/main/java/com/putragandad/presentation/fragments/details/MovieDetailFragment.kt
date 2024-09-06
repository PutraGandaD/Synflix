package com.putragandad.presentation.fragments.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.putragandad.synflix.domain.models.movies.MovieCast
import com.putragandad.synflix.common.utils.Constant
import com.putragandad.synflix.common.utils.Resource
import com.putragandad.synflix.presentation.R
import com.putragandad.synflix.presentation.adapters.MovieCastAdapter
import com.putragandad.synflix.presentation.databinding.FragmentMovieDetailBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class MovieDetailFragment : Fragment() {
    private var _binding: FragmentMovieDetailBinding? = null
    private val binding get() = _binding!!

    private val detailViewModel : MovieDetailViewModel by inject()

    private lateinit var movieId: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
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
            movieId = moviesId.toString()
            detailViewModel.getMovieDetails(movieId)
        }

        observer()
        setOnClickListener()
    }

    private fun observer() {
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                detailViewModel.detailUiState.collectLatest { uiState ->
                    uiState.movieDetails?.let {
                        setMovieDetails(it.title, it.releaseDate, it.overview, it.posterPath)
                    }

                    uiState.movieCast?.let {
                        setUpRvMovieCast(it)
                    }

                    uiState.message?.let {
                        Snackbar.make(requireView(), it, Snackbar.LENGTH_LONG)
                            .setAction("Retry") {
                                detailViewModel.getMovieDetails(movieId)
                            }
                            .show()
                        detailViewModel.messageShowed()
                    }

                    if(uiState.hasInternetConnection) {
                        binding.layoutDetailContent.visibility = View.VISIBLE
                        binding.noInternetLayout.root.visibility = View.GONE
                    } else {
                        binding.layoutDetailContent.visibility = View.GONE
                        binding.noInternetLayout.root.visibility = View.VISIBLE
                    }
                }
            }
        }
    }

    private fun setOnClickListener() {
        binding.noInternetLayout.btnRetry.setOnClickListener {
            detailViewModel.getMovieDetails(movieId)
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