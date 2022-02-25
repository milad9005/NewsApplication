package com.example.newsapp.data.entity.dto


data class ArticleDto(
    val author: String? =null,
    val content: String? =null,
    val description: String? =null,
    val publishedAt: String? =null,
    val source: Source? =null,
    val title: String? =null,
    val url: String? =null,
    val urlToImage: String? =null
)


//fun ArticleDto.toArticle(): RecArticleModel.ArticleModel {
//    return RecArticleModel.ArticleModel(
//        author = author,
//        content = content,
//        description = description,
//        publishedAt = publishedAt?.toTimeAgo(),
//        source = source?.name,
//        title = title,
//        urlToImage = urlToImage,
//        url = url
//    )
