package com.example.newsapp_v2.data.repository

import com.example.newsapp_v2.data.api.sources.Source
import com.example.newsapp_v2.data.model.Constants
import com.example.newsapp_v2.dataSource.SourcesDataSourceContract
import com.example.newsapp_v2.repository.sourcesRepo.SourcesRepoContract

class SoursesRepositoryImpl(val sourcesDataSource : SourcesDataSourceContract) : SourcesRepoContract {
    override suspend fun getSources(categoryData: String): List<Source?>? {
        val re = sourcesDataSource.getSources(categoryData)
        return re
    }

}