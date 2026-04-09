package com.route.newsappc43gsunwed.domain.repository.news

import com.route.newsappc43gsunwed.domain.model.ArticlesItem
import com.route.newsappc43gsunwed.domain.model.SourcesItem
import com.route.newsappc43gsunwed.domain.model.Result

interface NewsRepository {
    suspend fun getSources(categoryID: String): Result<List<SourcesItem>>
    suspend fun getArticles(sourceID: String): Result<List<ArticlesItem>>
}

interface NewsOnlineDataSource {
    suspend fun fetchSources(categoryID: String): Result<List<SourcesItem>>
    suspend fun fetchArticles(sourceID: String): Result<List<ArticlesItem>>
}

interface NewsOfflineDataSource {
    suspend fun getSources(categoryID: String): Result<List<SourcesItem>>
    suspend fun saveSources(sources: List<SourcesItem>, categoryId: String): Result<Unit>
    suspend fun getArticles(sourceID: String): Result<List<ArticlesItem>>
    suspend fun saveArticles(sources: List<ArticlesItem>, sourceId: String?): Result<Unit>
}
