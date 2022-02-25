package com.example.newsapp.ui


data class NewsListState(
    val isLoading: Boolean = false,
    val news: List<RecArticle.Article> = emptyList(),
    val error: String = ""
)
