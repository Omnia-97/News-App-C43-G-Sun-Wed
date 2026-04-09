package com.route.newsappc43gsunwed.di

import android.content.Context
import com.route.newsappc43gsunwed.data.database.NewsDatabase
import com.route.newsappc43gsunwed.data.database.dao.ArticlesDao
import com.route.newsappc43gsunwed.data.database.dao.SourcesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideSourcesDao(database: NewsDatabase): SourcesDao {
        return database.getSourcesDao()
    }

    @Provides
    @Singleton
    fun provideArticlesDao(database: NewsDatabase): ArticlesDao {
        return database.getArticlesDao()
    }

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): NewsDatabase {
        return NewsDatabase.init(context)
    }
}
