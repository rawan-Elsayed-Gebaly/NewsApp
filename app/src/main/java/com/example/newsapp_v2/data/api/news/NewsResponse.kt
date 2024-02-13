package com.example.newsapp_v2.data.api.news

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class NewsResponse(

    @field:SerializedName("totalResults")
	val totalResults: Int? = null,

    @field:SerializedName("articles")
	val articles: List<News?>? = null,

    @field:SerializedName("status")
	val status: String? = null,
    @field:SerializedName("code")
	val code: String? = null,
    @field:SerializedName("message")
	val message: String? = null
) : Parcelable