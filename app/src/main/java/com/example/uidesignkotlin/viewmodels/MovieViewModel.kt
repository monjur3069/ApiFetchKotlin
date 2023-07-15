package com.example.uidesignkotlin.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uidesignkotlin.models.MovieModel
import com.example.uidesignkotlin.repository.MovieRepository
import kotlinx.coroutines.launch

class MovieViewModel : ViewModel() {
    val repository = MovieRepository()
    val movieModelLD: MutableLiveData<MovieModel> = MutableLiveData()

    fun fetchData() {
        viewModelScope.launch {
            try {
                movieModelLD.value = repository.fetchMovieData()
            }catch (e: Exception) {
                Log.d("MovieViewModel", e.localizedMessage!!)
            }
        }
    }
}