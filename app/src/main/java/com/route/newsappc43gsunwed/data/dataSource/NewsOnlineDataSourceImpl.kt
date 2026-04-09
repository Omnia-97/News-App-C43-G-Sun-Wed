package com.route.newsappc43gsunwed.data.dataSource

import com.google.gson.Gson
import com.route.newsappc43gsunwed.data.api.NewsService
import com.route.newsappc43gsunwed.data.mapper.toArticleItem
import com.route.newsappc43gsunwed.data.mapper.toSourceItem
import com.route.newsappc43gsunwed.data.model.ArticlesResponseDM
import com.route.newsappc43gsunwed.domain.model.Result
import com.route.newsappc43gsunwed.data.model.SourcesResponseDM
import com.route.newsappc43gsunwed.domain.model.ArticlesItem
import com.route.newsappc43gsunwed.domain.model.SourcesItem
import com.route.newsappc43gsunwed.domain.repository.news.NewsOnlineDataSource
import javax.inject.Inject

class NewsOnlineDataSourceImpl @Inject constructor(
    private val newsService: NewsService
) : NewsOnlineDataSource {

    override suspend fun fetchSources(categoryID: String): Result<List<SourcesItem>> {
        try {
            val response = newsService.getSources(categoryID)
            if (response.isSuccessful) {
                val sources = response.body()?.sources ?: listOf()
                return Result.Success(sources.map { it.toSourceItem() })
            } else {
                val errorString = response.errorBody()?.string()
                val errorModel = Gson().fromJson(errorString, SourcesResponseDM::class.java)
                return Result.Error(errorModel.message ?: "")
            }
        } catch (e: Exception) {
            return Result.Error(e.message ?: "")
        }
    }

    override suspend fun fetchArticles(sourceID: String): Result<List<ArticlesItem>> {
        try {
            val response = newsService.getNewsBySourceId(sourceID)
            if (response.isSuccessful) {
                val articles = response.body()?.articles ?: listOf()
                return Result.Success(articles.map { it.toArticleItem() })
            } else {
                val errorString = response.errorBody()?.string()
                val errorModel = Gson().fromJson(errorString, ArticlesResponseDM::class.java)
                return Result.Error(errorModel.message ?: "")
            }
        } catch (e: Exception) {
            return Result.Error(e.message ?: "")
        }
    }

}
