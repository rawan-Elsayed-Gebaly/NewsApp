package com.example.newsapp_v2.viewModel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp_v2.data.api.news.News
import com.example.newsapp_v2.data.api.news.NewsResponse
import com.example.newsapp_v2.data.api.sources.Source
import com.example.newsapp_v2.data.api.sources.SourcesResponse
import com.example.newsapp_v2.data.model.Constants
import com.example.newsapp_v2.repository.articles.ArticlesRepo
import com.example.newsapp_v2.repository.search.SearchRepo
import com.example.newsapp_v2.repository.sourcesRepo.NewsSourcesRepository
import com.example.newsapp_v2.ui.ViewError
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor (
    val sourcesRepo: NewsSourcesRepository ,
    val articlesRepo : ArticlesRepo ,
    val searchRepo : SearchRepo
) : ViewModel ()
 {
    val shouldShowLoading = MutableLiveData<Boolean>()
    val sourcesLiveData = MutableLiveData<List<Source?>?>()
    val newsLiveData = MutableLiveData<List<News?>?>()
    val newsDetailsLiveData = MutableLiveData<News?>()
    val errorLiveData = MutableLiveData<ViewError>()
    val data = MutableLiveData<String>()
    val searchedKeyLiveData = MutableLiveData<String>()

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
                val searchResponse = searchRepo.getSearchResults(
                    searchedKeyLiveData.value.toString()
                )
                newsLiveData.postValue(searchResponse)
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
                        getNewsSources(categoryData)
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
                val news = articlesRepo.getNews(sourceId ?: "")
                newsLiveData.postValue(news)
            } catch (e: HttpException) {

                var errorBodyJsonString: String? = e.response()?.errorBody()?.string()
                var errorResponse = Gson().fromJson(
                    errorBodyJsonString, SourcesResponse::class.java
                )
                errorLiveData.postValue(
                    ViewError(message = errorResponse.message) {
                        getNews(sourceId)
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
