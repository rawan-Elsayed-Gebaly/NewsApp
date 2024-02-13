package com.example.newsapp_v2.data.dataSource

import com.example.newsapp_v2.data.api.news.News
import com.example.newsapp_v2.data.model.Constants
import com.example.newsapp_v2.data.model.WebServices
import com.example.newsapp_v2.dataSource.SearchDataSource
import javax.inject.Inject

class SearchDataSourceImpl @Inject constructor(val webServices: WebServices):SearchDataSource {
    override suspend fun getSearchResults(sources: String): List<News?>? {
        val result = webServices.getSearchedNews(Constants.apiKey , sources)
        return result.articles
    }
}