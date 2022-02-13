package com.example.mvvmwithretrofit.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import android.view.LayoutInflater
import android.view.View
import com.example.mvvmwithretrofit.databinding.LayoutItemBinding



class UserRecyclerAdapter(val userList: ArrayList<com.example.mvvmwithretrofit.model.movie.Result>, onMyItemClickListener: OnMyItemClickListener) : RecyclerView.Adapter<UserRecyclerAdapter.ViewHolder>() {

    var  onMyItemClickListener: OnMyItemClickListener

init {
    this.onMyItemClickListener = onMyItemClickListener;
}

    //this method is returning the view for each item in the list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserRecyclerAdapter.ViewHolder {
//        val v = LayoutInflater.from(parent.context).inflate(R.layout.layout_item, parent, false)
//        return ViewHolder(v)
        val layoutInflater = LayoutInflater.from(parent.context)
        val productRowBinding: LayoutItemBinding =
            LayoutItemBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(productRowBinding,onMyItemClickListener)
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: UserRecyclerAdapter.ViewHolder, position: Int) {
        holder.bindItems(userList[position])
//        holder.itemView.setOnClickListener {
//            Log.d("Myy",position.toString())
//        }
//        holder.itemView.setOnClickListener { listener(item) }

    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return userList.size
    }

    //the class is hodling the list view
    class ViewHolder(val binding: LayoutItemBinding, onMyItemClickListener: OnMyItemClickListener) : RecyclerView.ViewHolder(binding.root) , View.OnClickListener {
        var  onMyItemClickListener2: OnMyItemClickListener
        init {
            this.onMyItemClickListener2 = onMyItemClickListener
        }
        fun bindItems(movie: com.example.mvvmwithretrofit.model.movie.Result) {
            binding.movie = movie
            itemView.setOnClickListener(this)
//            binding.txtTitle.text = movie.title
//            binding.txtRating.text = movie.vote_count.toString()
//            Glide.with(itemView.context).load(user1.imageUrl).into(binding.imagePoster)

        }

        override fun onClick(p0: View?) {
            onMyItemClickListener2.onItemClick(adapterPosition)
        }
    }
    interface OnMyItemClickListener{
         fun onItemClick(position: Int)
    }
}