package com.example.mvvmwithretrofit.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmwithretrofit.model.movie.MoviesList
import com.example.mvvmwithretrofit.model.movieDetail.MovieDeatils
import com.example.mvvmwithretrofit.repository.MovieRepository
import com.example.mvvmwithretrofit.repository.Response
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val repository: MovieRepository) : ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getQuotes(1)


        }
    }

    val movieList: LiveData<Response<MoviesList>>
        get() = repository.movieList

    val movie: LiveData<Response<MovieDeatils>>
        get() = repository.movie

    fun getMovieDetail(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getMovieDetails(id)
        }
    }

    fun getSearchDetail(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getSearchDetails(query)
        }
    }
}