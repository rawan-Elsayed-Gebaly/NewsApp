package com.example.newsapp_v2.dataSource

import com.example.newsapp_v2.data.api.news.News

interface SearchDataSource {
    suspend fun getSearchResults(sources: String):List<News?>?

}