package com.route.newsappc43gsunwed.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.route.newsappc43gsunwed.data.model.ArticlesItemDM

@Dao
interface ArticlesDao {
    @Query("SELECT * FROM Articles WHERE  sourceId= :sourceId")
    suspend fun getArticlesBySourceId(sourceId: String): List<ArticlesItemDM>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticles(articlesItemDM: List<ArticlesItemDM>)
}
