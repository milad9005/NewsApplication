package com.reva.domain.usecase.base

import com.example.newsapp.common.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

abstract class UseCase<T, P>(
    private val coroutineContext: CoroutineContext
) {
    abstract suspend fun buildUseCase(params: P): Resource<T>

    fun execute(uiScheduler: CoroutineScope, params: P, onResult: (Resource<T>) -> Unit = {}) {
        uiScheduler.launch(coroutineContext) {
            onResult(buildUseCase(params))
        }
    }
}