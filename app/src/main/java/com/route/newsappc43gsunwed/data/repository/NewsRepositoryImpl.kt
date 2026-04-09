package com.route.newsappc43gsunwed.data.repository

import com.route.newsappc43gsunwed.data.dataSource.NewsOfflineDataSourceImpl
import com.route.newsappc43gsunwed.data.dataSource.NewsOnlineDataSourceImpl
import com.route.newsappc43gsunwed.data.model.ArticlesItemDM
import com.route.newsappc43gsunwed.domain.model.Result
import com.route.newsappc43gsunwed.data.model.SourcesItemDM
import com.route.newsappc43gsunwed.domain.model.ArticlesItem
import com.route.newsappc43gsunwed.domain.model.SourcesItem
import com.route.newsappc43gsunwed.domain.repository.news.NewsOfflineDataSource
import com.route.newsappc43gsunwed.domain.repository.news.NewsOnlineDataSource
import com.route.newsappc43gsunwed.domain.repository.news.NewsRepository
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val onlineDataSource: NewsOnlineDataSource,
    private val offlineDataSource: NewsOfflineDataSource
) : NewsRepository {

    override suspend fun getSources(categoryID: String): Result<List<SourcesItem>> {
        var isConnectedToInternet = true
        if (isConnectedToInternet) {
            // 1-  call the endpoint
            // 2-  Cache to the list of sources
            // 3-  return the list of sources
            val response = onlineDataSource.fetchSources(categoryID)
            return when (response) {
                is Result.Error -> {
                    response
                }

                is Result.Success -> {
                    offlineDataSource.saveSources(response.data, categoryID)
                    response
                }
            }

        } else {
            // return cached list of sources
            return offlineDataSource.getSources(categoryID)
        }
    }

    override suspend fun getArticles(sourceID: String): Result<List<ArticlesItem>> {
        var isConnectedToInternet = true
        if (isConnectedToInternet) {
            // 1-  call the endpoint
            // 2-  Cache to the list of articles
            // 3-  return the list of articles
            val response = onlineDataSource.fetchArticles(sourceID)
            return when (response) {
                is Result.Error -> {
                    response
                }

                is Result.Success -> {
                    offlineDataSource.saveArticles(response.data, sourceID)
                    response
                }
            }
        } else {
            // return cached list of sources
            return offlineDataSource.getArticles(sourceID)
        }
    }

// Clean Architecture
// Connectivity handling
}
