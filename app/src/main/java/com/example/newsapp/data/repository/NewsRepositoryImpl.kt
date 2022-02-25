package com.example.newsapp.data.repository

import com.example.newsapp.common.Resource
import com.example.newsapp.data.remote.GetNewsParam
import com.example.newsapp.data.remote.NewsList
import com.example.newsapp.data.remote.Remote
import com.example.newsapp.domain.repository.NewsRepository
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val remote: Remote
):NewsRepository{
    //    override suspend fun getNews(): NewsDto {
//        return api.getNews("us",API_KEY)
//    }
    override suspend fun getNews(
        getNewsParam: GetNewsParam,
        page: Int,
        take: Int
    ): Resource<NewsList> {
        return remote.getNewsWithPaging(getNewsParam,page, take)
    }
}