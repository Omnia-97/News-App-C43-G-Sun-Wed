package com.route.newsappc43gsunwed.data.dataSource

import android.util.Log
import com.route.newsappc43gsunwed.data.database.NewsDatabase
import com.route.newsappc43gsunwed.data.database.dao.ArticlesDao
import com.route.newsappc43gsunwed.data.database.dao.SourcesDao
import com.route.newsappc43gsunwed.data.mapper.toArticleItem
import com.route.newsappc43gsunwed.data.mapper.toArticlesItemDM
import com.route.newsappc43gsunwed.data.mapper.toSourceItem
import com.route.newsappc43gsunwed.data.mapper.toSourceItemDM
import com.route.newsappc43gsunwed.domain.model.Result
import com.route.newsappc43gsunwed.domain.model.ArticlesItem
import com.route.newsappc43gsunwed.domain.model.SourcesItem
import com.route.newsappc43gsunwed.domain.repository.news.NewsOfflineDataSource
import javax.inject.Inject

class NewsOfflineDataSourceImpl @Inject constructor(
    private val sourcesDao: SourcesDao,
    private val articlesDao: ArticlesDao
) : NewsOfflineDataSource {
    override suspend fun getSources(categoryID: String): Result<List<SourcesItem>> {
        return try {
            val result = sourcesDao.getSourcesByCategory(categoryID)
            Log.e("Success", "getSources: ${result} ")
            Result.Success(result.map { it.toSourceItem() })
        } catch (e: Exception) {
            Log.e("Error", "getSources: ${e.message} ")
            Result.Error(e.message ?: "")
        }
    }

    override suspend fun saveSources(sources: List<SourcesItem>, categoryId: String): Result<Unit> {
        return try {
            val sourcesTemp = sources.map { it.copy(category = categoryId) }
            Log.e("Sources", "saveSources:  ${sourcesTemp.map { it.category }}")
            Log.e("Sources", "saveSources 2:  ${sourcesTemp.map { it.toSourceItemDM() }}")
            sourcesDao.insertSources(sourcesTemp.map { it.toSourceItemDM() })
            Result.Success(Unit)
        } catch (e: Exception) {
            Result.Error(e.message ?: "")
        }
    }

    override suspend fun getArticles(sourceID: String): Result<List<ArticlesItem>> {
        return try {
            val result = articlesDao.getArticlesBySourceId(sourceID)
            Log.e("Success", "getArticles: ${result} ")
            Result.Success(result.map { it.toArticleItem() })
        } catch (e: Exception) {
            Log.e("Error", "getArticles: ${e.message} ")
            Result.Error(e.message ?: "")
        }
    }

    override suspend fun saveArticles(
        sources: List<ArticlesItem>,
        sourceId: String?
    ): Result<Unit> {
        return try {
            val newList = sources.map { it.copy(sourceId = sourceId) }
            articlesDao.insertArticles(newList.map { it.toArticlesItemDM() })
            Result.Success(Unit)
        } catch (e: Exception) {
            Result.Error(e.message ?: "")
        }
    }

}