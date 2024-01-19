package com.example.newsapp_v2.repository.search

import com.example.newsapp_v2.data.api.news.News
import com.example.newsapp_v2.data.api.news.NewsResponse

interface SearchRepo {
    suspend fun getSearchResults(sources: String):List<News?>?
}