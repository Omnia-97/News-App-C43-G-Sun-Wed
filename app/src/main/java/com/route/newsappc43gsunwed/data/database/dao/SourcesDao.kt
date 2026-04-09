package com.route.newsappc43gsunwed.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.route.newsappc43gsunwed.data.model.SourcesItemDM

@Dao
interface SourcesDao {
    @Query("SELECT * FROM Sources WHERE category = :categoryId")
    suspend fun getSourcesByCategory(categoryId: String): List<SourcesItemDM>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSources(sourcesItemDM: List<SourcesItemDM>)
}
