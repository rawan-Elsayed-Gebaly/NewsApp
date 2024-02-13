package com.example.newsapp_v2.dataSource

import com.example.newsapp_v2.data.api.news.News

interface ArticlesDataSource {
    suspend fun getNews(sourceId : String):List<News?>?
}