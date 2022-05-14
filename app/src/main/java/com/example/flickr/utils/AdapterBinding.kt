package com.example.flickr.utils

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.flickr.R
import com.squareup.picasso.Picasso

@BindingAdapter("app:image")
fun ImageView.image(image:String){

    Picasso.with(context)
        .load(image)
        .placeholder(R.drawable.loading_animation)
        .resize(300, 300)
        .into(this)

}
@BindingAdapter("app:text")
fun text(text: TextView, string:String){
    text.text=string

}