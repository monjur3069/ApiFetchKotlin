package com.example.uidesignkotlin


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout

import androidx.fragment.app.activityViewModels
import com.example.uidesignkotlin.databinding.FragmentHomeBinding
import com.example.uidesignkotlin.models.NowPlayingModel
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
        movieViewModel.nowPlayingModelLD.observe(viewLifecycleOwner) { nowPlayingModel ->
            // Hide progress indicator
            binding.progressBar.visibility = View.GONE
            setNowPlayingData(nowPlayingModel)
            Log.d(TAG, "${nowPlayingModel.page}")
        }
        binding.appbarLeading.setOnClickListener {
            binding.homeDrawer.openDrawer(GravityCompat.START)
        }
        return binding.root
    }

    private fun setNowPlayingData(nowPlayingModel: NowPlayingModel) {
        binding.appbarTitle.text = nowPlayingModel.results[1].title
    }
}