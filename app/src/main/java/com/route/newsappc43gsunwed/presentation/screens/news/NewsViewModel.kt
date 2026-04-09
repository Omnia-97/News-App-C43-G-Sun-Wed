package com.route.newsappc43gsunwed.presentation.screens.news

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.route.newsappc43gsunwed.domain.model.ArticlesItem
import com.route.newsappc43gsunwed.domain.model.Result
import com.route.newsappc43gsunwed.domain.model.SourcesItem
import com.route.newsappc43gsunwed.domain.usecases.GetArticlesUseCase
import com.route.newsappc43gsunwed.domain.usecases.GetSourcesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val getSourcesUseCase: GetSourcesUseCase,
    private val getArticlesUseCase: GetArticlesUseCase
) : ViewModel() {

    val isLoading = MutableLiveData(false)
    val sourcesLiveData = MutableLiveData<List<SourcesItem>>()
    val articlesLiveData = MutableLiveData<List<ArticlesItem>>()
    val errorLiveData = MutableLiveData("")
    fun getSourcesByCategory(categoryApiId: String) {
        isLoading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            val response = getSourcesUseCase.invoke(categoryApiId)
            isLoading.postValue(false)
            when (response) {
                is Result.Error -> {
                    errorLiveData.postValue(response.errorMessage)
                }

                is Result.Success -> {
                    sourcesLiveData.postValue(response.data)
                }
            }
        }
        //.execute()  X // Execute ->  Main Thread or UI Thread
    }

    fun getArticlesBySourceId(sourceId: String) {
        isLoading.postValue(true)
        viewModelScope.launch(Dispatchers.IO) {
            val response = getArticlesUseCase.invoke(sourceId)
            isLoading.postValue(false)
            when (response) {
                is Result.Error -> {
                    errorLiveData.postValue(response.errorMessage)
                }

                is Result.Success -> {
                    articlesLiveData.postValue(response.data)
                }
            }
        }
    }
    //  1- Kotlin Coroutines
    //  2- Clean Architecture (Repository Pattern)
}
