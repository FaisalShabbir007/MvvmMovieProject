package com.example.mvvmwithretrofit.api

import com.example.mvvmwithretrofit.model.movie.MoviesList
import com.example.mvvmwithretrofit.model.movieDetail.MovieDeatils
import com.example.mvvmwithretrofit.util.Constants.Companion.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {


    @GET("/3/movie/popular?api_key=" + API_KEY)
    suspend fun getMovies(@Query("page") page: Int): Response<MoviesList>

    @GET("/3/search/movie?")
    suspend fun searchMovie(
        @Query("api_key") api_key: String,
        @Query("query") query: String,
        @Query("page") page: Int
    ): Response<MoviesList>

    //    @GET("/3/movie/343611?api_key="+API_KEY)
    @GET("/3/movie/{movie_id}?")
    suspend fun getMovie(
        @Path("movie_id") movie_id: Int,
        @Query("api_key") api_key: String
    ): Response<MovieDeatils>


}