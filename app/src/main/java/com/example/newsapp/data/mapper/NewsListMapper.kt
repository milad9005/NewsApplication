package com.example.newsapp.data.mapper

import com.example.newsapp.data.remote.NewsList
import com.example.newsapp.data.entity.dto.NewsDto
import javax.inject.Inject

class NewsListMapper @Inject constructor(
    private val newsMapper: NewsMapper
) : MapperToDomain<NewsDto, NewsList> {
    override fun mapToDomain(from: NewsDto): NewsList {
        return NewsList(
            articleList = from.articles.map { newsMapper.mapToDomain(it) }
        )
    }
}