package com.example.newsapp.ui

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.newsapp.R
import com.example.newsapp.ui.utils.Visitable




sealed class RecArticle: Visitable {


    @Entity(tableName = "articles")
    data class Article(

        @PrimaryKey(autoGenerate = true)
        var id:Int?=null,
        val author: String,
        val content: String,
        val description: String,
        val publishedAt: String,
        val source: String,
        val title: String,
        val url: String,
        val urlToImage: String,
        val onClick: (Int?) -> Unit,
    ) : RecArticle() {

        fun onClick() {
            this.onClick(id)
        }

        override fun type(): Int {
            return R.layout.news_item
        }

    }
}
