package com.route.newsappc43gsunwed.data.api

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {
    // Localization
    // Logging Interceptor
    private val apiKeyValue = "c027443ca9624422bfbe9b160b9ec11a"
    override fun intercept(chain: Interceptor.Chain): Response {
        val oldRequest = chain.request()
        val newUrlBuilder = oldRequest.url.newBuilder()  //    https://newsapi.org/v2/   everything
        newUrlBuilder.addQueryParameter("apiKey", apiKeyValue) //    https://newsapi.org/v2/   everything?apiKey=lahlajsfga
        val newUrl = newUrlBuilder.build()
        val newRequest = oldRequest.newBuilder().url(newUrl).build()
        return chain.proceed(newRequest)
    }
}
