package com.example.newsapp.data.remote

import com.example.newsapp.common.Const.API_KEY

data class GetNewsParam(
    val country: String = "us",
    val category: String="business",
    val apiKey: String = API_KEY,
    )