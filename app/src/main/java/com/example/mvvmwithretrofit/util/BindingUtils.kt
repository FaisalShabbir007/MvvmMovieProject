package com.example.mvvmwithretrofit.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.mvvmwithretrofit.util.Constants.Companion.IMAGE_BASE_URL

@BindingAdapter("image")
fun loadImage(view : ImageView, url : String){
    Glide.with(view)
        .load(IMAGE_BASE_URL + url)
        .into(view)
}

//@BindingAdapter("profileImage")
//fun loadImage(view: ImageView, imageUrl: String?) {
//    Glide.with(view.getContext())
//        .load(imageUrl).apply(RequestOptions().circleCrop())
//        .into(view)
//}