package com.example.newsapp.data.remote

import com.example.newsapp.common.Const.API_KEY
import com.example.newsapp.common.NetworkResponse
import com.example.newsapp.data.entity.ErrorResponse
import com.example.newsapp.data.entity.dto.NewsDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {


    @GET("v2/top-headlines")
    suspend fun getNews(
        @Query("country") countryCode: String,
        @Query("pageSize") pageSize:Int,
        @Query("page") page: Int,
        @Query("apiKey") apiKey: String,
    ): NetworkResponse<NewsDto,ErrorResponse>


    @GET("v2/everything")
    suspend fun searchRequest(
        @Query("q") quert: String,
        @Query("from") fromDate: String ,
        @Query("to") toDate: String,
        @Query("sortBy") sortBy: String = "popularity",
        @Query("apiKey") apiKey: String = API_KEY,
    ): Response<NewsDto>


}