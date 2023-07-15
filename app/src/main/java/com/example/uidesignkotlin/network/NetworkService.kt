package com.example.uidesignkotlin.network

import com.example.uidesignkotlin.models.MovieModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Url

const val base_url = "https://api.themoviedb.org/3/movie/"

val retrofit = Retrofit.Builder()
    .baseUrl(base_url)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

interface MovieApi {
    @GET()
    suspend fun getMovieData(@Url endUrl: String) : MovieModel
}

object NetworkService {
    val movieApi: MovieApi by lazy {
        retrofit.create(MovieApi::class.java)
    }
}