package com.route.newsappc43gsunwed.domain.usecases

import com.route.newsappc43gsunwed.domain.model.Result
import com.route.newsappc43gsunwed.domain.model.SourcesItem
import com.route.newsappc43gsunwed.domain.repository.news.NewsRepository
import javax.inject.Inject

class GetSourcesUseCase @Inject constructor(
    private val repository: NewsRepository
) {
    suspend fun invoke(categoryID: String): Result<List<SourcesItem>> {
        return repository.getSources(categoryID)
    }
}
