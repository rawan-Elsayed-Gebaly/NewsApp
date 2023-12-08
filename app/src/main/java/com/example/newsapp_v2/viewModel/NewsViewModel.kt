package com.example.newsapp_v2.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newsapp_v2.api.news.News
import com.example.newsapp_v2.api.news.NewsResponse
import com.example.newsapp_v2.api.sources.Source
import com.example.newsapp_v2.api.sources.SourcesResponse
import com.example.newsapp_v2.model.ApiManager
import com.example.newsapp_v2.model.Constants
import com.example.newsapp_v2.ui.ViewError
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsViewModel:ViewModel() {
    val shouldShowLoading = MutableLiveData<Boolean>()
    val sourcesLiveData = MutableLiveData<List<Source?>?>()
    val newsLiveData = MutableLiveData<List<News?>?>()
    val newsDetailsLiveData = MutableLiveData<News?>()
    val errorLiveData= MutableLiveData<ViewError>()
    val data = MutableLiveData<String>()
    val searchedKeyLiveData = MutableLiveData<String>()

    fun setData(value: String) {
        data.value = value
    }

    fun getData(): LiveData<String> {
        return data
    }


    fun getSearchedNews(){
        shouldShowLoading.postValue(true)
        ApiManager.getApi()
            .getSearchedNews(Constants.apiKey,
                searchedKeyLiveData.value.toString()
            )
            .enqueue(object:Callback<NewsResponse>{
                override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                    shouldShowLoading.postValue(false)
                    errorLiveData.postValue(
                        ViewError(t = t) {
                            getSearchedNews()
                        })
                }

                override fun onResponse(
                    call: Call<NewsResponse>,
                    response: Response<NewsResponse>
                ) {
                    shouldShowLoading.postValue(false)
                    Log.e("T", "API Request Sucesses:${searchedKeyLiveData.value.toString()}")
                    if(response.isSuccessful){
                        newsLiveData.postValue(response.body()?.articles)

                    }else{
                        var errorBodyFromGson = response.errorBody()?.string()
                        var errorResponse = Gson().fromJson(errorBodyFromGson,
                            NewsResponse::class.java)
                        errorLiveData.postValue(
                            ViewError(message= errorResponse.message) {
                                getSearchedNews()
                            })
                    }
                }
            })

    }


    fun getNewsSources(categoryData:String) {
        shouldShowLoading.postValue(true)
        ApiManager.getApi()
                .getSource(Constants.apiKey , categoryData)
                .enqueue(object : Callback<SourcesResponse> {
                    override fun onResponse(
                        call: Call<SourcesResponse>,
                        response: Response<SourcesResponse>
                    ) {
                        shouldShowLoading.postValue(false)
                        if(response.isSuccessful)
                        {
                            sourcesLiveData.postValue(response.body()?.sources)
                        }
                        else{
                            var errorBodyJsonString: String? =  response.errorBody()?.string()
                            var errorResponse  = Gson().fromJson(errorBodyJsonString
                                , SourcesResponse::class.java)
                          errorLiveData.postValue(
                            ViewError(message = errorResponse.message) {
                                getSearchedNews()
                            })
                            }
                    }
                    override fun onFailure(call: Call<SourcesResponse>, t: Throwable) {
                        shouldShowLoading.postValue(false)
                        errorLiveData.postValue(
                            ViewError(t = t) {
                                getNewsSources(categoryData)
                            })
                    }
                })
        }

    fun getNews(sourceId: String?) {
        shouldShowLoading.postValue(true)
        // this fun will get id and call the api
        if (sourceId != null) {
            ApiManager
                .getApi()
                .getNews(Constants.apiKey , sourceId)
                .enqueue(object :Callback<NewsResponse>{
                    override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                        shouldShowLoading.postValue(false)
                         errorLiveData.postValue(
                             ViewError(t = t ){
                                 getNews(sourceId)
                             }
                         )
                    }

                    override fun onResponse(
                        call: Call<NewsResponse>,
                        response: Response<NewsResponse>
                    ) {
                        shouldShowLoading.postValue(false)
                        if(response.isSuccessful){
                            //show news
                            newsLiveData.postValue(response.body()?.articles)

                        }
                        else{
                            val responseErrorBodyJson = response.errorBody()?.string()
                            var errorResponse = Gson().fromJson(responseErrorBodyJson,
                                NewsResponse::class.java)
                                errorLiveData.postValue(
                                    ViewError(message = errorResponse.message){
                                        getNews(sourceId)
                                    }
                                )

                        }
                    }
                })
        }

    }

}
