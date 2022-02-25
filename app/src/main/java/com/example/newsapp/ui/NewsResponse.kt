package com.example.newsapp.ui

import com.example.newsapp.data.entity.dto.ArticleDto

data class NewsResponse(
    val articles: List<ArticleDto>,
    val status: String,
    val totalResults: Int,
    val code:String,
    val message:String?
)