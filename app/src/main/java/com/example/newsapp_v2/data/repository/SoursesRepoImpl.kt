package com.example.newsapp_v2.data.repository

import com.example.newsapp_v2.data.api.sources.Source
import com.example.newsapp_v2.dataSource.NewsSourcesDataSource
import com.example.newsapp_v2.repository.sourcesRepo.NewsSourcesRepository
import javax.inject.Inject

class SoursesRepoImpl @Inject constructor(private val sourcesDataSource : NewsSourcesDataSource)
    : NewsSourcesRepository {
    override suspend fun getSources(categoryData: String): List<Source?>? {
        val repository = sourcesDataSource.getSources(categoryData)
        return repository
    }

}