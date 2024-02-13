package com.example.newsapp_v2.data.model

import com.example.newsapp_v2.data.api.news.NewsResponse
import com.example.newsapp_v2.data.api.sources.SourcesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WebServices {

    @GET("v2/top-headlines/sources")
    suspend fun getSource(
        @Query("apikey") apiKey: String,
        @Query("category") category: String
    ): SourcesResponse

    @GET("v2/everything")
    suspend fun getNews(
        @Query("apiKey") apiKey: String = Constants.apiKey,
        @Query("sources") sources: String
    ): NewsResponse


    @GET("v2/everything")
    suspend fun getSearchedNews(
        @Query("apiKey") apiKey: String = Constants.apiKey,
        @Query("q") sources: String
    ): NewsResponse
}