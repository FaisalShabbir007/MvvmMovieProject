package com.example.mvvmwithretrofit.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.BaseObservable
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.mvvmwithretrofit.R
import com.example.mvvmwithretrofit.databinding.ActivityDetailScreenBinding
import com.example.mvvmwithretrofit.repository.Response
import com.example.mvvmwithretrofit.util.Constants
import com.example.mvvmwithretrofit.viewmodels.MainModelViewFactory
import com.example.mvvmwithretrofit.viewmodels.MainViewModel

class DetailScreenActivity : AppCompatActivity() {
    lateinit var mainViewModel : MainViewModel
    lateinit var binding: ActivityDetailScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail_screen)

        mainViewModel = ViewModelProvider(this, MainModelViewFactory(Constants.repository)).get(MainViewModel::class.java)

        val id = intent.getIntExtra("id",0)
                Log.d("Myy",id.toString())
        mainViewModel.getMovieDetail(id)

        mainViewModel.movie.observe(this, Observer {
            when(it){
                is Response.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Response.Success -> {
                    it.data?.let {
//                        Log.d("MYCODE",it.title)
                        binding.txtTitle.text = it.title
                        binding.txtOverview.text = "Overview : "+it.overview
                        binding.txtRevenew.text =  "Revenue: "+ it.revenue.toString()
                        binding.txtDuration.text = "Duration: "+ it.runtime.toString()
                        binding.txtMovieVoteAvg.text = it.vote_average.toString()
                        binding.txtMovieVoteCount.text = " ("+it.vote_count.toString()+")"
                        Glide.with(this).load(Constants.IMAGE_BASE_URL + it.backdrop_path).into(binding.imageViewBg)
                        binding.progressBar.visibility = View.GONE
                    }
                }
                is Response.Error -> {
                    Log.d("MYCODE2",resources.getString(R.string.some_error))
                }
            }

        })

    }
}