package com.example.uidesignkotlin


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.GravityCompat

import androidx.fragment.app.activityViewModels
import com.example.uidesignkotlin.databinding.FragmentHomeBinding
import com.example.uidesignkotlin.models.MovieModel
import com.example.uidesignkotlin.viewmodels.MovieViewModel


class HomeFragment : Fragment() {

    private val TAG = "HomeFragment"
    private lateinit var binding: FragmentHomeBinding
    private val movieViewModel: MovieViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        // Show progress indicator
        binding.progressBar.visibility = View.VISIBLE

        // Fetch data first
        movieViewModel.fetchData()

        // Observe the LiveData after fetching data
        movieViewModel.movieModelLD.observe(viewLifecycleOwner) { movieModel ->
            // Hide progress indicator
            binding.progressBar.visibility = View.GONE
            setMovieData(movieModel)
            Log.d(TAG, "${movieModel.page}")
        }
        binding.appbarLeading.setOnClickListener {
            binding.homeDrawer.openDrawer(GravityCompat.START)
        }
        return binding.root
    }

    private fun setMovieData(movieModel: MovieModel) {
        binding.appbarTitle.text = movieModel.results[1].title
    }
}