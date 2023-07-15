package com.example.uidesignkotlin.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uidesignkotlin.models.NowPlayingModel
import com.example.uidesignkotlin.repository.MovieRepository
import kotlinx.coroutines.launch

class MovieViewModel : ViewModel() {
    val repository = MovieRepository()
    val nowPlayingModelLD: MutableLiveData<NowPlayingModel> = MutableLiveData()

    fun fetchData() {
        viewModelScope.launch {
            try {
                nowPlayingModelLD.value = repository.fetchNowPlayingData()
            }catch (e: Exception) {
                Log.d("MovieViewModel", e.localizedMessage!!)
            }
        }
    }
}