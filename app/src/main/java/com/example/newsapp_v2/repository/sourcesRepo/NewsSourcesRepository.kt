package com.example.newsapp_v2.repository.sourcesRepo

import com.example.newsapp_v2.data.api.sources.Source

interface NewsSourcesRepository {
  suspend fun getSources(categoryData: String):List<Source?>?

}