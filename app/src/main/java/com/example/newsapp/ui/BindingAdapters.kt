package com.example.newsapp.ui

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.newsapp.R
import com.makeramen.roundedimageview.RoundedImageView
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.NetworkPolicy

import com.squareup.picasso.Picasso

@BindingAdapter("bind:imageUrl")
fun loadImage(view: RoundedImageView, imageUrl: String) {

    Picasso.get()
        .load(imageUrl)
        .placeholder(R.drawable.placeholder)
        .error(R.drawable.error_plase_holder)
        .into(view)
}


