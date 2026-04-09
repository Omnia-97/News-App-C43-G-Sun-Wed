package com.route.newsappc43gsunwed.presentation

import android.app.Application
import com.route.newsappc43gsunwed.data.database.NewsDatabase
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class NewsApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        NewsDatabase.init(this)
    }
}
