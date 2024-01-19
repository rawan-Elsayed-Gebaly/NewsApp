package com.example.newsapp_v2.data.repository

import com.example.newsapp_v2.data.api.news.News
import com.example.newsapp_v2.data.dataSource.NewsSourcesOnlineDataSourceImpl
import com.example.newsapp_v2.dataSource.SearchDataSource
import com.example.newsapp_v2.repository.search.SearchRepo
import javax.inject.Inject

class SearchRepoImpl @Inject constructor(private val searchDataSource: SearchDataSource) : SearchRepo {
    override suspend fun getSearchResults(sources: String): List<News?>? {
        val result = searchDataSource.getSearchResults(sources)
        return result

    }
}