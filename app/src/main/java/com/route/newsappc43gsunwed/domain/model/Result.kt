package com.route.newsappc43gsunwed.domain.model

sealed class Result<T> {
    data class Success<T>(val data: T) : Result<T>()
    data class Error<T>(val errorMessage: String) : Result<T>()
}
