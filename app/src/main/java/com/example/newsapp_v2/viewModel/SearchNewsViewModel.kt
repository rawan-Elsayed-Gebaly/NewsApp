package com.example.newsapp_v2.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newsapp_v2.api.news.NewsResponse
import com.example.newsapp_v2.model.ApiManager
import com.example.newsapp_v2.model.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchNewsViewModel:ViewModel() {
    val searchedNewsLiveData = MutableLiveData<String>()

    fun getSearchedNews(searchedKey:String){
        ApiManager.getApi()
            .getSearchedNews(searchedKey , Constants.apiKey)
            .enqueue(object: Callback<NewsResponse> {
                override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                    TODO("Not yet implemented")
                }

                override fun onResponse(
                    call: Call<NewsResponse>,
                    response: Response<NewsResponse>
                ) {
                    TODO("Not yet implemented")
                }
            })

    }

}