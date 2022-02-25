package com.reva.domain.usecase.base

import com.example.newsapp.common.Resource
import kotlin.coroutines.CoroutineContext

abstract class UseCaseWithPaging<T, P>(
    private val coroutineContext: CoroutineContext
) {
    abstract suspend fun buildUseCase(params: P, page: Int, take: Int): Resource<T>
}