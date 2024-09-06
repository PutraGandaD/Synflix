package com.putragandad.presentation.fragments.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.perf.metrics.AddTrace
import com.putragandad.synflix.domain.models.movies.NowPlaying
import com.putragandad.synflix.domain.models.movies.Popular
import com.putragandad.synflix.domain.models.movies.TopRated
import com.putragandad.synflix.common.utils.Constant
import com.putragandad.synflix.presentation.R
import com.putragandad.synflix.presentation.adapters.NowPlayingAdapter
import com.putragandad.synflix.presentation.adapters.NowPlayingClickListener
import com.putragandad.synflix.presentation.adapters.PopularAdapter
import com.putragandad.synflix.presentation.adapters.PopularClickListener
import com.putragandad.synflix.presentation.adapters.TopRatedAdapter
import com.putragandad.synflix.presentation.adapters.TopRatedClickListener
import com.putragandad.synflix.presentation.databinding.FragmentHomeBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class HomeFragment : Fragment(), NowPlayingClickListener, TopRatedClickListener,
    PopularClickListener {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val homeViewModel: HomeViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observer()
        onClickListener()
    }

    private fun observer() {
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                homeViewModel.homeUiState.collectLatest { uiState ->
                    uiState.movieNowPlaying?.let {
                        setUpRvNowPlaying(it)
                    }

                    uiState.moviePopular?.let {
                        setUpRvPopular(it)
                    }

                    uiState.movieTopRated?.let {
                        setUpRvTopRated(it)
                    }

                    uiState.message?.let {
                        Snackbar.make(requireView(), it, Snackbar.LENGTH_LONG)
                            .setAction("Retry") {
                                homeViewModel.initializeHomeScreen()
                            }
                            .show()
                        homeViewModel.messageShowed()
                    }

                    if(uiState.hasInternetConnection) {
                        binding.layoutHomeContent.visibility = View.VISIBLE
                        binding.noInternetLayout.root.visibility = View.GONE
                    } else {
                        binding.layoutHomeContent.visibility = View.GONE
                        binding.noInternetLayout.root.visibility = View.VISIBLE
                    }
                }
            }
        }
    }

    private fun onClickListener() {
        binding.noInternetLayout.btnRetry.setOnClickListener {
            homeViewModel.initializeHomeScreen()
        }
    }

    private fun setUpRvNowPlaying(dataset: List<NowPlaying>) {
        val shimmer = binding.nowPlayingShimmering

        shimmer.startShimmer()

        if(dataset.isNotEmpty()) {
            val adapter = NowPlayingAdapter(dataset, requireActivity(), this)
            val recyclerView : RecyclerView? = view?.findViewById(R.id.now_playing_rv_container)
            recyclerView?.adapter = adapter
            recyclerView?.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)

            shimmer.apply {
                stopShimmer()
                visibility = View.GONE
            }

            recyclerView?.visibility = View.VISIBLE
        }
    }

    private fun setUpRvPopular(dataset: List<Popular>) {
        val shimmer = binding.popularShimmering

        shimmer.startShimmer()

        if(dataset.isNotEmpty()) {
            val adapter = PopularAdapter(dataset, requireActivity(), this)
            val recyclerView : RecyclerView? = view?.findViewById(R.id.popular_rv_container)
            recyclerView?.adapter = adapter
            recyclerView?.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)

            shimmer.apply {
                stopShimmer()
                visibility = View.GONE
            }

            recyclerView?.visibility = View.VISIBLE
        }
    }

    private fun setUpRvTopRated(dataset: List<TopRated>) {
        val shimmer = binding.topratedShimmering

        shimmer.startShimmer()

        if(dataset.isNotEmpty()) {
            val adapter = TopRatedAdapter(dataset, requireActivity(), this)
            val recyclerView : RecyclerView? = view?.findViewById(R.id.toprated_rv_container)
            recyclerView?.adapter = adapter
            recyclerView?.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)

            shimmer.apply {
                stopShimmer()
                visibility = View.GONE
            }

            recyclerView?.visibility = View.VISIBLE
        }
    }

    @AddTrace(name = "on_click_movie_nowplaying", enabled = true)
    override fun onClickMovieNowPlaying(result: NowPlaying) {
        val bundle = bundleOf(Constant.MOVIES_ID_EXTRA to result.id)
        findNavController().navigate(R.id.action_homeFragment_to_movieDetailFragment, bundle)
    }

    @AddTrace(name = "on_click_movie_popular", enabled = true)
    override fun onClickPopularMovie(result: Popular) {
        val bundle = bundleOf(Constant.MOVIES_ID_EXTRA to result.id)
        findNavController().navigate(R.id.action_homeFragment_to_movieDetailFragment, bundle)
    }

    @AddTrace(name = "on_click_movie_toprated", enabled = true)
    override fun onClickMovieTopRated(result: TopRated) {
        val bundle = bundleOf(Constant.MOVIES_ID_EXTRA to result.id)
        findNavController().navigate(R.id.action_homeFragment_to_movieDetailFragment, bundle)
    }
}