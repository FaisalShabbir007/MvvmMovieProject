package com.example.mvvmwithretrofit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmwithretrofit.activities.DetailScreenActivity
import com.example.mvvmwithretrofit.adapters.UserRecyclerAdapter
import com.example.mvvmwithretrofit.databinding.ActivityMainBinding
import com.example.mvvmwithretrofit.model.movie.Result
import com.example.mvvmwithretrofit.repository.Response
import com.example.mvvmwithretrofit.util.Constants.Companion.repository
import com.example.mvvmwithretrofit.viewmodels.MainModelViewFactory
import com.example.mvvmwithretrofit.viewmodels.MainViewModel

class MainActivity : AppCompatActivity(), UserRecyclerAdapter.OnMyItemClickListener  {
    lateinit var mainViewModel : MainViewModel
    lateinit var binding : ActivityMainBinding
     var movies = ArrayList<Result>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        mainViewModel = ViewModelProvider(this,MainModelViewFactory(repository)).get(MainViewModel::class.java)
        //creating our adapter
        val adapter = UserRecyclerAdapter(movies,this)


        //now adding the adapter to recyclerview
        binding.recyclerView.adapter = adapter

        mainViewModel.movieList.observe(this, Observer {
            when(it){
                is Response.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Response.Success -> {
                 it.data?.let {
                     if(it.results.size == 0) {
                         binding.txtEmpty.visibility = View.VISIBLE
                         binding.txtEmpty.text = resources.getString(R.string.no_data)
//                         Toast.makeText(this, resources.getString(R.string.no_data),
//                             Toast.LENGTH_LONG).show();
                     }
                     else{
                     it.results.forEach {
                         movies.add(it)
                     }
                         binding.txtEmpty.visibility = View.GONE
                     }
                     adapter.notifyDataSetChanged()
                     binding.progressBar.visibility = View.GONE
//                     Log.d("MYCODE",it.results.toString())
                 }


                }
                is Response.Error -> {
                    Log.d("MYCODE2",resources.getString(R.string.some_error))
                }
            }

        })
        binding.btnSearch.setOnClickListener {
            binding.txtEmpty.visibility = View.GONE
            binding.progressBar.visibility = View.VISIBLE
            val query = binding.editSearch.text.toString()
            if(query.isNullOrEmpty()){
                Toast.makeText(this, resources.getString(R.string.empty_error),
                    Toast.LENGTH_LONG).show();
            }else{
            movies.clear()
            mainViewModel.getSearchDetail(query)
            adapter.notifyDataSetChanged()
            }
        }

    }

    override fun onItemClick(position: Int) {
        val intent = Intent(this,DetailScreenActivity::class.java)
        intent.putExtra("id",movies.get(position).id)
        startActivity(intent)
    }
}