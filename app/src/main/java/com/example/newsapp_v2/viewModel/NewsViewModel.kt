package com.example.newsapp_v2.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp_v2.data.api.news.News
import com.example.newsapp_v2.data.api.news.NewsResponse
import com.example.newsapp_v2.data.api.sources.Source
import com.example.newsapp_v2.data.api.sources.SourcesResponse
import com.example.newsapp_v2.data.dataSource.SourcesOnlineDataSourceImpl
import com.example.newsapp_v2.data.model.ApiManager
import com.example.newsapp_v2.data.model.Constants
import com.example.newsapp_v2.data.repository.SoursesRepositoryImpl
import com.example.newsapp_v2.repository.sourcesRepo.SourcesRepoContract
import com.example.newsapp_v2.ui.ViewError
import com.google.gson.Gson
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response

class NewsViewModel : ViewModel() {
    val shouldShowLoading = MutableLiveData<Boolean>()
    val sourcesLiveData = MutableLiveData<List<Source?>?>()
    val newsLiveData = MutableLiveData<List<News?>?>()
    val newsDetailsLiveData = MutableLiveData<News?>()
    val errorLiveData = MutableLiveData<ViewError>()
    val data = MutableLiveData<String>()
    val searchedKeyLiveData = MutableLiveData<String>()
    val sourcesOnlineData: SourcesOnlineDataSourceImpl = SourcesOnlineDataSourceImpl(
        ApiManager.getApi()
    )
    val sourcesRepo: SourcesRepoContract = SoursesRepositoryImpl(
        sourcesOnlineData
    )

    fun setData(value: String) {
        data.value = value
    }

    fun getData(): LiveData<String> {
        return data
    }

    fun getSearchedNews() {
        shouldShowLoading.postValue(true)
        viewModelScope.launch {
            try {
                val searchResponse = ApiManager.getApi()
                    .getSearchedNews(
                        Constants.apiKey,
                        searchedKeyLiveData.value.toString()
                    )
                newsLiveData.postValue(searchResponse.articles)
            } catch (e: HttpException) {
                var errorBodyFromGson = e.response()?.errorBody()?.string()
                var errorResponse = Gson().fromJson(
                    errorBodyFromGson,
                    NewsResponse::class.java
                )
                errorLiveData.postValue(
                    ViewError(message = errorResponse.message) {
                        getSearchedNews()
                    })
            } catch (e: Exception) {
                errorLiveData.postValue(
                    ViewError(t = e) {
                        getSearchedNews()
                    }
                )
            } finally {
                shouldShowLoading.postValue(false)
            }

        }

    }

    fun clearSearchData() {
        searchedKeyLiveData.postValue("")
    }

    fun getNewsSources(categoryData: String) {
        viewModelScope.launch {
            try {
                val sources = sourcesRepo.getSources(
                    categoryData
                )
                sourcesLiveData.postValue(sources)

            } catch (e: HttpException) {
                var errorBodyJsonString: String? = e.response()?.errorBody()?.string()
                var errorResponse = Gson().fromJson(
                    errorBodyJsonString, SourcesResponse::class.java
                )
                errorLiveData.postValue(
                    ViewError(message = errorResponse.message) {
                        getSearchedNews()
                    }
                )
            } catch (e: Exception) {
                errorLiveData.postValue(
                    ViewError(t = e) {
                        getNewsSources(categoryData)
                    }
                )
            } finally {
                shouldShowLoading.postValue(false)
            }
        }

    }

    fun getNews(sourceId: String?) {
        shouldShowLoading.postValue(true)
        viewModelScope.launch {
            try {
                val newsResponse = ApiManager
                    .getApi()
                    .getNews(
                        sources = sourceId ?: ""
                    )
                newsLiveData.postValue(newsResponse.articles)
            } catch (e: HttpException) {

                var errorBodyJsonString: String? = e.response()?.errorBody()?.string()
                var errorResponse = Gson().fromJson(
                    errorBodyJsonString, SourcesResponse::class.java
                )
                errorLiveData.postValue(
                    ViewError(message = errorResponse.message) {
                        getSearchedNews()
                    }
                )

            } catch (e: Exception) {
                shouldShowLoading.postValue(false)
                errorLiveData.postValue(
                    ViewError(t = e) {
                        getNews(sourceId)
                    }
                )
            } finally {
                shouldShowLoading.postValue(false)
            }

        }


    }

}
