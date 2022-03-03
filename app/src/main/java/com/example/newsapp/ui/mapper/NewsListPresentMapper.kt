package com.example.newsapp.ui.mapper

import com.example.newsapp.data.remote.NewsList
import com.example.newsapp.ui.NewsListItem
import com.example.newsapp.ui.RecArticle
import javax.inject.Inject

class NewsListPresentMapper @Inject constructor() {


    fun mapToPresentation(
        from:NewsList,
        onClick:(Int?)->Unit
    ) : NewsListItem{

        val newsList:MutableList<RecArticle> = from.articleList.map {

            RecArticle.Article(
                id=0,
                author = it.author ?: "",
                content = it.content ?: "",
                description = it.description ?: "",
                publishedAt = it.publishedAt ?: "",
                source =  it.source ?: "",
                title = it.title ?: "",
                url = it.url?:"",
                urlToImage = it.urlToImage?:"",
                onClick = onClick
            )

        }.toMutableList()

        return NewsListItem(
            newsList
        )
    }



}