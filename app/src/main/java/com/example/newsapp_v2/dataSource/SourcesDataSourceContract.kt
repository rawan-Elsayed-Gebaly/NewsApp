package com.example.newsapp_v2.dataSource

import com.example.newsapp_v2.data.api.sources.Source
import com.example.newsapp_v2.data.model.Constants
import com.example.newsapp_v2.data.model.WebServices

interface SourcesDataSourceContract {

    suspend fun getSources(categoryData: String): List<Source?>?

}