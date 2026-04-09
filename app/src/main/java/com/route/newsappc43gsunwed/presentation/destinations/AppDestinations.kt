package com.route.newsappc43gsunwed.presentation.destinations

import kotlinx.serialization.Serializable

@Serializable
data object SplashDestination

@Serializable
data object CategoriesDestination

@Serializable
data class NewsDestination(val categoryApiId: String)
