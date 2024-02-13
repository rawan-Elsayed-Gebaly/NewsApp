package com.example.newsapp_v2.repository.articles

import com.example.newsapp_v2.data.api.news.News

interface ArticlesRepo {
    suspend fun getNews(sourceId : String):List<News?>?
}