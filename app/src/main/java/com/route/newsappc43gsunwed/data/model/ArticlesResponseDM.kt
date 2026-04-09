package com.route.newsappc43gsunwed.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class ArticlesResponseDM(

    @field:SerializedName("totalResults")
    val totalResults: Int? = null,

    @field:SerializedName("articles")
    val articles: List<ArticlesItemDM>? = null,
    @field:SerializedName("message")
    val message: String? = null,
    @field:SerializedName("status")
    val status: String? = null
)

@Entity(tableName = "Articles")
data class ArticlesItemDM(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    @field:SerializedName("publishedAt")
    val publishedAt: String? = null,

    @field:SerializedName("author")
    val author: String? = null,

    @field:SerializedName("urlToImage")
    val urlToImage: String? = null,

    @field:SerializedName("description")
    val description: String? = null,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("url")
    val url: String? = null,

    @field:SerializedName("content")
    val content: String? = null,

    val sourceId: String? = null
)
