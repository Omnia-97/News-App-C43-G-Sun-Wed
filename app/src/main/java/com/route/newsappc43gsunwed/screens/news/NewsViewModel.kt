package com.route.newsappc43gsunwed.screens.news

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.route.newsappc43gsunwed.api.ApiManager
import com.route.newsappc43gsunwed.model.ArticlesItem
import com.route.newsappc43gsunwed.model.ArticlesResponse
import com.route.newsappc43gsunwed.model.SourcesItem
import com.route.newsappc43gsunwed.model.SourcesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsViewModel : ViewModel() {
    val isLoading = MutableLiveData(false)
    val sourcesLiveData = MutableLiveData<List<SourcesItem>>()
    val articlesLiveData = MutableLiveData<List<ArticlesItem>>()
    val errorLiveData = MutableLiveData("") // Runtime permissions & Google Maps
    fun getSourcesByCategory(categoryApiId: String) {
        isLoading.value = true
        ApiManager.getNewsService().getSources(categoryApiId = categoryApiId)
            .enqueue(object : Callback<SourcesResponse> {
                override fun onResponse(
                    call: Call<SourcesResponse>,
                    response: Response<SourcesResponse>
                ) {
                    isLoading.value = false
                    if (response.isSuccessful) {
                        val sources = response.body()?.sources ?: listOf()
                        sourcesLiveData.value = (sources)
                        Log.e("TAG", "onResponse: ${response.body()}")
                    } else {
                        // 500 , 400  ->
                        val errorString = response.errorBody()?.string()
                        val errorModel = Gson().fromJson(errorString, SourcesResponse::class.java)
                        errorLiveData.value = errorModel?.message ?: ""
                    }
                }

                override fun onFailure(p0: Call<SourcesResponse?>?, p1: Throwable?) {
                    isLoading.value = false
                    errorLiveData.value = p1?.message ?: ""
                    Log.e("Error", p1?.message ?: "")
                }
            })
        //.execute()  X // Execute ->  Main Thread or UI Thread
    }

    fun getArticlesBySourceId(sourceId: String) {
        isLoading.value = true
        ApiManager.getNewsService().getNewsBySourceId(sourceId).enqueue(
            object : Callback<ArticlesResponse> {
                override fun onResponse(
                    call: Call<ArticlesResponse>,
                    response: Response<ArticlesResponse>
                ) {

                    isLoading.value = false
                    if (response.isSuccessful) {
                        val articles = response.body()?.articles ?: listOf()
                        articlesLiveData.value = articles
                    } else {
                        // 500 , 400  ->
                        val errorString = response.errorBody()?.string()
                        val errorModel = Gson().fromJson(errorString, SourcesResponse::class.java)
                        errorLiveData.value = errorModel?.message ?: ""
                    }
                }

                override fun onFailure(
                    call: Call<ArticlesResponse?>?,
                    error: Throwable?
                ) {
                    isLoading.value = false
                    errorLiveData.value = error?.message ?: ""
                    Log.e("TAG", "onFailure: ${error?.message}")
                }

            }
        )
    }

}
