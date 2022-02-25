package com.example.newsapp.common

import java.io.IOException

sealed class Resource<out T>() {
    data class ServerError(val message: List<String>) : Resource<Nothing>()
    data class NetworkError(val error: IOException):Resource<Nothing>()
    data class UnknownError(val error: Throwable) : Resource<Nothing>()
    data class Success<T : Any>(val model: T) : Resource<T>()
}