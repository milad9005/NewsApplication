package com.example.newsapp.data.mapper

import com.example.newsapp.common.NetworkResponse
import com.example.newsapp.common.Resource
import com.example.newsapp.data.entity.ErrorResponse
import com.example.newsapp.ui.NewsResponse
import retrofit2.Response
import javax.inject.Inject

class NetworkResponseMapper<From : Any, To : Any> @Inject constructor() {
    fun mapToDomain(
        networkResponse: NetworkResponse<From, ErrorResponse>,
        mapperToDomain: MapperToDomain<From, To>
    ): Resource<To> {
        return when (networkResponse) {

            is NetworkResponse.Success<From> -> Resource.Success(
                mapperToDomain.mapToDomain(
                    networkResponse.body
                )
            )
            is NetworkResponse.ServerError<ErrorResponse> -> Resource.ServerError(
                networkResponse.body?.message ?: listOf()
            )
            is NetworkResponse.NetworkError -> Resource.NetworkError(networkResponse.error)
            is NetworkResponse.UnknownError -> Resource.UnknownError(networkResponse.error)
        }
    }
}