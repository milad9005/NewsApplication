package com.example.newsapp.data.remote

import retrofit2.Retrofit

class ServiceGenerator constructor(private val retrofit: Retrofit) {
    fun newsApi(): NewsApi =
        retrofit.create(NewsApi::class.java)

}
