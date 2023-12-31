package com.example.newsapp_v2.model

import com.example.newsapp_v2.api.news.NewsResponse
import com.example.newsapp_v2.api.sources.SourcesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WebServices  {
  //  https://newsapi.org/v2/top-headlines/sources?apiKey=f8d47718ead24fb8ad6be45dcbe60fd6
    @GET("v2/top-headlines/sources")
    fun getSource(
    @Query("apikey") apiKey:String,
    @Query("category") category:String
    ):Call<SourcesResponse>


    //https://newsapi.org/v2/everything?apiKey=f8d47718ead24fb8ad6be45dcbe60fd6&sources=abc-news-au
    @GET("v2/everything")
    fun getNews(
      @Query("apiKey")apiKey:String= Constants.apiKey,
    @Query("sources")sources:String
    ):Call<NewsResponse>


    @GET("v2/everything")
    fun getSearchedNews(
      @Query("apiKey")apiKey:String= Constants.apiKey,
      @Query("q")sources:String
    ):Call<NewsResponse>
}