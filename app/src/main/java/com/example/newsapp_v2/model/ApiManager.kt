package com.example.newsapp_v2.model

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

//  //  https://newsapi.org/v2/top-headlines/sources?apiKey=f8d47718ead24fb8ad6be45dcbe60fd6
class ApiManager {
   companion object{
       private var retrofit: Retrofit?=null
       private  fun getInstance():Retrofit{
           if(retrofit == null )
           {
               retrofit = Retrofit.Builder()
                   .baseUrl("https://newsapi.org/")
                   .addConverterFactory(GsonConverterFactory.create())
                   .build()
           }
           return retrofit!!
       }
       fun getApi():WebServices{
           return getInstance().create(WebServices::class.java)
       }
   }
}