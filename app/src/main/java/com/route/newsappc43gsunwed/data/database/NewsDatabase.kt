package com.route.newsappc43gsunwed.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.route.newsappc43gsunwed.data.database.dao.ArticlesDao
import com.route.newsappc43gsunwed.data.database.dao.SourcesDao
import com.route.newsappc43gsunwed.data.model.ArticlesItemDM
import com.route.newsappc43gsunwed.data.model.SourcesItemDM

@Database(version = 1, entities = [SourcesItemDM::class, ArticlesItemDM::class])
abstract class NewsDatabase : RoomDatabase() {
    abstract fun getSourcesDao(): SourcesDao
    abstract fun getArticlesDao(): ArticlesDao

    companion object {
        private var INSTANCE: NewsDatabase? = null
        private val DATABASE_NAME = "NewsAppDB"
        fun init(context: Context): NewsDatabase {
            if (INSTANCE == null) {
                INSTANCE =
                    Room.databaseBuilder(context, NewsDatabase::class.java, DATABASE_NAME)
                        .fallbackToDestructiveMigration(true)
                        .build()
            }
            return INSTANCE!!
        }

        fun getInstance(): NewsDatabase {
            return INSTANCE!!
        }
    }

}
