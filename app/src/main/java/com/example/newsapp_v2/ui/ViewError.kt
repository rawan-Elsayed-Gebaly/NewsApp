package com.example.newsapp_v2.ui

data class ViewError(
    val message :String?= null ,
    val t :Throwable ?= null,
    val onClick: OnTryAgainClickListener?= null
)

fun interface OnTryAgainClickListener{
    fun onTryAgainClick()
}