package com.route.newsappc43gsunwed.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class SourcesResponseDM(

    @field:SerializedName("sources")
    val sources: List<SourcesItemDM>? = null,
    @field:SerializedName("message")
    val message: String? = null,
    @field:SerializedName("status")
    val status: String? = null
)

@Entity(tableName = "Sources")
data class SourcesItemDM(

    @field:SerializedName("country")
    val country: String? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("description")
    val description: String? = null,

    @field:SerializedName("language")
    val language: String? = null,

    @PrimaryKey(autoGenerate = true)
    val primaryKeyId: Int? = null,

    @field:SerializedName("id")
    val id: String? = null,

    @field:SerializedName("category")
    val category: String? = null,

    @field:SerializedName("url")
    val url: String? = null
)
