package com.example.newsapp.data.entity.dto

data class NewsDto(
    val articles: List<ArticleDto>,
    val status: String?,
    val totalResults: Int?,
    val code: String?,
    val message: String?
)
