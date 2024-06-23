package com.putragandad.synflix.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.putragandad.synflix.R
import com.putragandad.synflix.presentation.adapters.NowPlayingAdapter
import com.putragandad.synflix.presentation.adapters.NowPlayingClickListener
import com.putragandad.synflix.presentation.adapters.PopularAdapter
import com.putragandad.synflix.presentation.adapters.PopularClickListener
import com.putragandad.synflix.presentation.adapters.TopRatedAdapter
import com.putragandad.synflix.presentation.adapters.TopRatedClickListener
import com.putragandad.synflix.databinding.FragmentHomeBinding
import com.putragandad.domain.models.movies.NowPlaying
import com.putragandad.domain.models.movies.Popular
import com.putragandad.domain.models.movies.TopRated
import com.putragandad.synflix.presentation.viewmodels.MoviesViewModel
import com.putragandad.common.utils.Constant
import com.putragandad.common.utils.Resource
import org.koin.android.ext.android.inject

class HomeFragment : Fragment(), NowPlayingClickListener, TopRatedClickListener,
    PopularClickListener {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val moviesViewModel: MoviesViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        moviesViewModel.movieNowPlaying.observe(viewLifecycleOwner) { movies ->
            when(movies) {
                is Resource.Success -> {
                    //Toast.makeText(requireActivity(), "SUCCESS", Toast.LENGTH_SHORT).show()
                    movies.data?.let {
                        setUpRvNowPlaying(it)
                    }
                }
                is Resource.Error -> {
                    //Toast.makeText(requireActivity(), "ERROR", Toast.LENGTH_SHORT).show()
                }
                is Resource.Loading -> {
                    //Toast.makeText(requireActivity(), "LOADING", Toast.LENGTH_SHORT).show()
                    setUpRvNowPlaying(emptyList())
                }
            }
        }

        moviesViewModel.moviePopular.observe(viewLifecycleOwner) { movies ->
            when(movies) {
                is Resource.Success -> {
                    movies.data?.let {
                        setUpRvPopular(it)
                    }
                }
                is Resource.Error -> {

                }
                is Resource.Loading -> {
                    setUpRvPopular(emptyList())
                }
            }
        }

        moviesViewModel.movieTopRated.observe(viewLifecycleOwner) { movies ->
            when(movies) {
                is Resource.Success -> {
                    movies.data?.let {
                        setUpRvTopRated(it)
                    }
                }
                is Resource.Error -> {

                }
                is Resource.Loading -> {
                    setUpRvTopRated(emptyList())
                }
            }
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

    override fun onClickMovieNowPlaying(result: NowPlaying) {
        val bundle = bundleOf(Constant.MOVIES_ID_EXTRA to result.id)
        findNavController().navigate(R.id.action_homeFragment_to_movieDetailFragment, bundle)
    }

    override fun onClickPopularMovie(result: Popular) {
        val bundle = bundleOf(Constant.MOVIES_ID_EXTRA to result.id)
        findNavController().navigate(R.id.action_homeFragment_to_movieDetailFragment, bundle)
    }

    override fun onClickMovieTopRated(result: TopRated) {
        val bundle = bundleOf(Constant.MOVIES_ID_EXTRA to result.id)
        findNavController().navigate(R.id.action_homeFragment_to_movieDetailFragment, bundle)
    }
}