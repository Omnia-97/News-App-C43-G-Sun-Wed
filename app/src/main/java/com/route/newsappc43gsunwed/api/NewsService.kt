package com.route.newsappc43gsunwed.api

import com.route.newsappc43gsunwed.model.ArticlesResponse
import com.route.newsappc43gsunwed.model.SourcesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {
    @GET("top-headlines/sources")
    fun getSources(
        @Query("category") categoryApiId: String,
    ): Call<SourcesResponse>

    @GET("everything")
    fun getNewsBySourceId(
        @Query("sources") sourceId: String,
    ): Call<ArticlesResponse>
}
// Saturday -> Facebook Timeline Jetpack compose
