package com.example.mvvmwithretrofit.util

import com.example.mvvmwithretrofit.api.MovieService
import com.example.mvvmwithretrofit.api.RetrofitHelper
import com.example.mvvmwithretrofit.repository.MovieRepository

class Constants {
    companion object{
        //https://api.themoviedb.org/3/movie/popular?api_key=83d01f18538cb7a275147492f84c3698&page=1
        //https://api.themoviedb.org/3/movie/popular?api_key=83d01f18538cb7a275147492f84c3698
//        const val BASE_URL = "https://quotable.io/"
        const val BASE_URL = "https://api.themoviedb.org/"
        const val API_KEY = "83d01f18538cb7a275147492f84c3698"
        const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500/"
        val movieService = RetrofitHelper.getInstance().create(MovieService::class.java)
        val repository = MovieRepository(movieService)


    }
}