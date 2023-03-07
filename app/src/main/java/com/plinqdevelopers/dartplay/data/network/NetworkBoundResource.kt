package com.plinqdevelopers.dartplay.data.network

import kotlinx.coroutines.flow.*

inline fun <ResultType, RequestType> networkBoundResource(
    crossinline query: () -> Flow<ResultType>,
    crossinline fetch: suspend () -> RequestType,
    crossinline saveFetchedResults: suspend (RequestType) -> Unit,
    crossinline shouldFetch: (ResultType) -> Boolean = { true }
) = flow {
    val listData = query().first()

    val flowResult = if (shouldFetch(listData)) {
        emit(Resources.Loading(listData))
        try {
            saveFetchedResults(fetch())
            query().map { Resources.Success(data = it) }
        } catch (throwable: Exception) {
            query().map { Resources.Error(data = it, throwableError = throwable) }
        }
    } else {
        query().map { Resources.Success(data = it) }
    }

    emitAll(flowResult)
}
