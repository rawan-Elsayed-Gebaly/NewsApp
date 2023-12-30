package com.example.newsapp_v2.data.dataSource

import com.example.newsapp_v2.data.api.sources.Source
import com.example.newsapp_v2.data.model.Constants
import com.example.newsapp_v2.data.model.WebServices
import com.example.newsapp_v2.dataSource.SourcesDataSourceContract
import com.example.newsapp_v2.repository.sourcesRepo.SourcesRepoContract

class SourcesOnlineDataSourceImpl (val webServices: WebServices): SourcesDataSourceContract {
    override suspend fun getSources(categoryData: String): List<Source?>? {
        val response = webServices.getSource(Constants.apiKey , categoryData)
        return response.sources
    }


}