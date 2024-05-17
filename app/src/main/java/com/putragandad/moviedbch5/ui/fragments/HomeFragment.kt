package com.putragandad.moviedbch5.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.putragandad.moviedbch5.R
import com.putragandad.moviedbch5.adapters.NowPlayingAdapter
import com.putragandad.moviedbch5.adapters.NowPlayingClickListener
import com.putragandad.moviedbch5.adapters.PopularAdapter
import com.putragandad.moviedbch5.adapters.PopularClickListener
import com.putragandad.moviedbch5.adapters.TopRatedAdapter
import com.putragandad.moviedbch5.adapters.TopRatedClickListener
import com.putragandad.moviedbch5.databinding.FragmentHomeBinding
import com.putragandad.moviedbch5.models.now_playing.NowPlayingResult
import com.putragandad.moviedbch5.models.popular.PopularResult
import com.putragandad.moviedbch5.models.top_rated.TopRatedResult
import com.putragandad.moviedbch5.ui.viewmodels.MoviesViewModel
import com.putragandad.moviedbch5.ui.viewmodels.MoviesViewModelFactory
import com.putragandad.moviedbch5.utils.Constant

class HomeFragment : Fragment(), NowPlayingClickListener, TopRatedClickListener, PopularClickListener {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val moviesViewModel : MoviesViewModel by viewModels {
        MoviesViewModelFactory.getInstance(requireActivity())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        moviesViewModel.getMovieNowPlaying().observe(requireActivity()) { movies ->
            val result = movies.results
            setUpRvNowPlaying(result)

//            Log.d("OUTPUT", "$movies")
//            Log.d("RESULT", "$result")
        }

        moviesViewModel.getMoviePopular().observe(requireActivity()) { movies ->
            val result = movies.results
            setUpRvPopular(result)
        }

        moviesViewModel.getMovieTopRated().observe(requireActivity()) { movies ->
            val result = movies.results
            setUpRvTopRated(result)
        }
    }

    private fun setUpRvNowPlaying(dataset: List<NowPlayingResult>) {
        val adapter = NowPlayingAdapter(dataset, requireActivity(), this)
        val recyclerView : RecyclerView? = view?.findViewById(R.id.now_playing_rv_container)
        recyclerView?.adapter = adapter
        recyclerView?.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
    }

    private fun setUpRvPopular(dataset: List<PopularResult>) {
        val adapter = PopularAdapter(dataset, requireActivity(), this)
        val recyclerView : RecyclerView? = view?.findViewById(R.id.popular_rv_container)
        recyclerView?.adapter = adapter
        recyclerView?.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
    }

    private fun setUpRvTopRated(dataset: List<TopRatedResult>) {
        val adapter = TopRatedAdapter(dataset, requireActivity(), this)
        val recyclerView : RecyclerView? = view?.findViewById(R.id.toprated_rv_container)
        recyclerView?.adapter = adapter
        recyclerView?.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
    }

    override fun onClickMovieNowPlaying(result: NowPlayingResult) {
        val bundle = bundleOf(Constant.MOVIES_ID_EXTRA to result.id)
        findNavController().navigate(R.id.action_homeFragment_to_movieDetailFragment, bundle)
    }

    override fun onClickPopularMovie(result: PopularResult) {
        val bundle = bundleOf(Constant.MOVIES_ID_EXTRA to result.id)
        findNavController().navigate(R.id.action_homeFragment_to_movieDetailFragment, bundle)
    }

    override fun onClickMovieTopRated(result: TopRatedResult) {
        val bundle = bundleOf(Constant.MOVIES_ID_EXTRA to result.id)
        findNavController().navigate(R.id.action_homeFragment_to_movieDetailFragment, bundle)
    }
}