package com.example.newsapp_v2.data.repository

import com.example.newsapp_v2.data.api.news.News
import com.example.newsapp_v2.dataSource.ArticlesDataSource
import com.example.newsapp_v2.repository.articles.ArticlesRepo
import javax.inject.Inject

class ArticlesRepoImpl @Inject constructor (val newsOnlineDataSource : ArticlesDataSource) : ArticlesRepo {
    override suspend fun getNews(sourceId: String): List<News?>? {
        val new = newsOnlineDataSource.getNews(sourceId)
        return new
    }
}