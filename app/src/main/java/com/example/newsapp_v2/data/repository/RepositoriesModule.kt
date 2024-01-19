package com.example.newsapp_v2.data.repository

import com.example.newsapp_v2.data.dataSource.ArticlesOnlineDataSourceImpl
import com.example.newsapp_v2.data.dataSource.NewsSourcesOnlineDataSourceImpl
import com.example.newsapp_v2.data.dataSource.SearchDataSourceImpl
import com.example.newsapp_v2.dataSource.ArticlesDataSource
import com.example.newsapp_v2.dataSource.NewsSourcesDataSource
import com.example.newsapp_v2.dataSource.SearchDataSource
import com.example.newsapp_v2.repository.articles.ArticlesRepo
import com.example.newsapp_v2.repository.search.SearchRepo
import com.example.newsapp_v2.repository.sourcesRepo.NewsSourcesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoriesModule {
    @Binds
    abstract fun provideSearchDataSource(searchDataSourceImpl: SearchDataSourceImpl):
            SearchDataSource
    @Binds
    abstract fun providesSearchRepo(searchRepoImpl: SearchRepoImpl):SearchRepo


    @Binds
    abstract fun provideArticlesRepo(repoImpl: ArticlesRepoImpl)
            : ArticlesRepo

    @Binds
    abstract fun provideArticlesDataSource(dataSourceImpl: ArticlesOnlineDataSourceImpl)
            : ArticlesDataSource

    @Binds
    abstract fun provideNewsSources(
        repoImpl: SoursesRepoImpl
    )
            : NewsSourcesRepository

    @Binds
    abstract fun provideNewsSourcesDataSource(
        dataSource: NewsSourcesOnlineDataSourceImpl
    )
    : NewsSourcesDataSource


}