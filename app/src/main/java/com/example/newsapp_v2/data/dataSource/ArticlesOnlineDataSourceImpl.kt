package com.example.newsapp_v2.data.dataSource

import com.example.newsapp_v2.data.api.news.News
import com.example.newsapp_v2.data.model.Constants
import com.example.newsapp_v2.data.model.WebServices
import com.example.newsapp_v2.dataSource.ArticlesDataSource
import javax.inject.Inject

class ArticlesOnlineDataSourceImpl @Inject constructor ( private val webServices: WebServices)
    : ArticlesDataSource {
    override suspend fun getNews(sourceId: String): List<News?>? {
        val new = webServices.getNews(Constants.apiKey , sourceId)
        return new.articles
    }
}