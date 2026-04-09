package com.route.newsappc43gsunwed.di

import com.route.newsappc43gsunwed.data.api.NewsService
import com.route.newsappc43gsunwed.data.dataSource.NewsOfflineDataSourceImpl
import com.route.newsappc43gsunwed.data.dataSource.NewsOnlineDataSourceImpl
import com.route.newsappc43gsunwed.data.database.dao.ArticlesDao
import com.route.newsappc43gsunwed.data.database.dao.SourcesDao
import com.route.newsappc43gsunwed.data.repository.NewsRepositoryImpl
import com.route.newsappc43gsunwed.domain.repository.news.NewsOfflineDataSource
import com.route.newsappc43gsunwed.domain.repository.news.NewsOnlineDataSource
import com.route.newsappc43gsunwed.domain.repository.news.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideNewsOnlineDataSource(service: NewsService): NewsOnlineDataSource {
        return NewsOnlineDataSourceImpl(service)
    }

    @Provides
    @Singleton
    fun provideNewsOfflineDataSource(
        sourcesDao: SourcesDao,
        articlesDao: ArticlesDao
    ): NewsOfflineDataSource {
        return NewsOfflineDataSourceImpl(sourcesDao, articlesDao)
    }

    @Provides
    @Singleton
    fun provideNewsRepository(
        onlineDataSource: NewsOnlineDataSource,
        offlineDataSource: NewsOfflineDataSource
    ): NewsRepository {
        return NewsRepositoryImpl(onlineDataSource, offlineDataSource)
    }
}
