package com.example.newsapp_v2.ui.home.adapter

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
class AdapterDi{
    @Provides
    fun provideNewsAdapter():NewsAdapter{
        return NewsAdapter(listOf())
    }

}