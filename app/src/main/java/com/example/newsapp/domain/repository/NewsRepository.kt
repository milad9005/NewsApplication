package com.example.newsapp.domain.repository

import com.example.newsapp.common.Resource
import com.example.newsapp.data.remote.GetNewsParam
import com.example.newsapp.data.remote.NewsList

interface NewsRepository {

    suspend fun getNews(
        getNewsParam: GetNewsParam,
        page: Int,
        take: Int
    ): Resource<NewsList>

}