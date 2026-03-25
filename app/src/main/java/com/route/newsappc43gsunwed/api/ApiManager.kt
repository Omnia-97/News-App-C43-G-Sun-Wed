package com.route.newsappc43gsunwed.api

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiManager {
    private val BASE_URL = "https://newsapi.org/v2/"
    private val httpLoggingInterceptor = HttpLoggingInterceptor { message ->
        Log.e("API", message)
    }.apply {
        level = HttpLoggingInterceptor.Level.BODY
        // -> https://newsapi.org/v2/
        //         <- 200 https://newsapi.org/v2/
    }
    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(AuthInterceptor())
        .addInterceptor(httpLoggingInterceptor)
        .build()

    private
    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getNewsService(): NewsService {
        return retrofit.create(NewsService::class.java)
    }
}

// HTTP Logging Interceptor
// Interceptors
//
