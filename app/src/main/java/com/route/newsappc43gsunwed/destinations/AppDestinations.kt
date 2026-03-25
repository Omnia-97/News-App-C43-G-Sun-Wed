package com.route.newsappc43gsunwed.destinations

import com.route.newsappc43gsunwed.model.CategoryDM
import kotlinx.serialization.Serializable

@Serializable
data object SplashDestination

@Serializable
data object CategoriesDestination

@Serializable
data class NewsDestination(val categoryApiId: String)
