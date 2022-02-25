package com.example.newsapp.data.mapper

import com.example.newsapp.data.entity.dto.ArticleDto
import com.example.newsapp.domain.model.ArticleModel
import com.example.newsapp.ui.utils.toTimeAgo
import javax.inject.Inject

class NewsMapper @Inject constructor() : MapperToDomain<ArticleDto, ArticleModel> {
    //    override fun mapToDomain(from: NewsDto): List<ArticleModel> {
//
//        return from.articles.map {
//            ArticleModel(
//                author = it.author,
//                content = it.content,
//                description = it.description,
//                publishedAt = it.publishedAt?.toTimeAgo(),
//                source = it.source?.name,
//                title = it.title,
//                urlToImage = it.urlToImage,
//                url = it.url
//            )
//        }
//
//
//    }
    override fun mapToDomain(from: ArticleDto) = ArticleModel(
        author = from.author,
        content = from.content,
        description = from.description,
        publishedAt = from.publishedAt?.toTimeAgo(),
        source = from.source?.name,
        title = from.title,
        url = from.url,
        urlToImage = from.urlToImage
    )
}