package com.example.newsapp.data.remote

import com.example.newsapp.common.Resource
import com.example.newsapp.data.entity.dto.NewsDto
import com.example.newsapp.data.mapper.NetworkResponseMapper
import com.example.newsapp.data.mapper.NewsListMapper
import com.example.newsapp.ui.NewsResponse
import javax.inject.Inject

class Remote @Inject constructor(
    private val serviceGenerator: ServiceGenerator,
    private val newsResponseMapper: NetworkResponseMapper<NewsDto, NewsList>,
    private val newsListMapper: NewsListMapper
) {

    suspend fun getNewsWithPaging(
        getNewsParam: GetNewsParam,
        page: Int,
        take: Int

    ): Resource<NewsList> {
        return newsResponseMapper.mapToDomain(
            serviceGenerator.newsApi().getNews(
                getNewsParam.country,
                take,
                page,
                getNewsParam.apiKey
            ),
            newsListMapper
        )
    }


}