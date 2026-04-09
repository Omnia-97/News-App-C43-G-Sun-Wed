package com.route.newsappc43gsunwed.data.api

import com.route.newsappc43gsunwed.data.model.ArticlesResponseDM
import com.route.newsappc43gsunwed.data.model.SourcesResponseDM
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

// Retrofit
interface NewsService {
    @GET("top-headlines/sources")
    suspend fun getSources(
        @Query("category") categoryApiId: String,
    ): Response<SourcesResponseDM> // 400  500

    // 1- suspend
    // 2-  Call  X                -> Response
    @GET("everything")
    suspend fun getNewsBySourceId(
        @Query("sources") sourceId: String,
    ): Response<ArticlesResponseDM>
}
// Saturday -> Facebook Timeline Jetpack compose
