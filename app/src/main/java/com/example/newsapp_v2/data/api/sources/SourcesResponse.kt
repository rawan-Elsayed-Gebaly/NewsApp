package com.example.newsapp_v2.data.api.sources

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.example.newsapp_v2.data.api.sources.Source
import com.google.gson.annotations.SerializedName

@Parcelize
data class SourcesResponse(

    @field:SerializedName("sources")
	val sources: List<Source?>? = null,

    @field:SerializedName("status")
	val status: String? = null,

    @field:SerializedName("message")
	val message: String? = null,

    @field:SerializedName("code")
	val code: String? = null
) : Parcelable