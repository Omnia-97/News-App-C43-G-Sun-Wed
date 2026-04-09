package com.route.newsappc43gsunwed.domain.usecases

import com.route.newsappc43gsunwed.domain.model.ArticlesItem
import com.route.newsappc43gsunwed.domain.model.Result
import com.route.newsappc43gsunwed.domain.model.SourcesItem
import com.route.newsappc43gsunwed.domain.repository.news.NewsRepository
import javax.inject.Inject

class GetArticlesUseCase @Inject constructor(
    private val repository: NewsRepository
) {
    suspend fun invoke(sourceID: String): Result<List<ArticlesItem>> {
        return repository.getArticles(sourceID)
    }
}
