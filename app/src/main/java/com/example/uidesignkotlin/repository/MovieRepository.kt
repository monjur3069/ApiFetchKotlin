package com.example.uidesignkotlin.repository

import com.example.uidesignkotlin.models.NowPlayingModel
import com.example.uidesignkotlin.network.NetworkService

class MovieRepository {
    suspend fun fetchNowPlayingData(): NowPlayingModel {
        val endUrl = "now_playing?api_key=1b11a9f6356e930cb7542b3606c403f9&language=en-US&page=1"
        return NetworkService.movieApi.getNowPlayingData(endUrl)
    }
}