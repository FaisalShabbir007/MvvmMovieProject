package com.example.mvvmwithretrofit.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mvvmwithretrofit.api.MovieService
import com.example.mvvmwithretrofit.model.movie.MoviesList
import com.example.mvvmwithretrofit.model.movieDetail.MovieDeatils
import com.example.mvvmwithretrofit.util.Constants.Companion.API_KEY
import java.lang.Exception

class MovieRepository(private val movieService: MovieService) {

    private val moviesLiveData = MutableLiveData<Response<MoviesList>>()
    private val movieDetailLiveDate = MutableLiveData<Response<MovieDeatils>>()

    val movieList : LiveData<Response<MoviesList>>
    get() = moviesLiveData

    val movie : LiveData<Response<MovieDeatils>>
        get() = movieDetailLiveDate

    suspend fun getQuotes(page : Int){
        try {
            val result = movieService.getMovies(page)
            if(result?.body() != null){
                moviesLiveData.postValue(Response.Success(result.body()))
            }else{
                moviesLiveData.postValue(Response.Error("API Error"))
            }
        }catch (e:Exception){
            moviesLiveData.postValue(Response.Error(e.message.toString()))

        }
    }
    suspend fun getMovieDetails(id: Int){
        try {
         val result = movieService.getMovie(id,API_KEY)
        if(result != null){
            movieDetailLiveDate.postValue(Response.Success(result.body()))
        }else{
            movieDetailLiveDate.postValue(Response.Error("API Error"))
        }
        }catch (e:Exception){
            movieDetailLiveDate.postValue(Response.Error(e.message.toString()))

        }
    }
    suspend fun getSearchDetails(query: String){
        try {
            val result = movieService.searchMovie(API_KEY,query,1)
            if(result != null){
                moviesLiveData.postValue(Response.Success(result.body()))
            }else{
                moviesLiveData.postValue(Response.Error("API Error"))
            }
        }catch (e:Exception){
            moviesLiveData.postValue(Response.Error(e.message.toString()))

        }
    }

}