package com.example.newsapp_v2.dataSource

import com.example.newsapp_v2.data.api.sources.Source

interface NewsSourcesDataSource {
    suspend fun getSources(categoryData: String): List<Source?>?

}