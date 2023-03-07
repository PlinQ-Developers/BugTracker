package com.plinqdevelopers.dartplay.data.network

sealed class Resources<T>(
    val data: T? = null,
    val errorMessage: Exception? = null
) {
    class Success<T>(data: T) : Resources<T>(data = data)
    class Loading<T>(data: T? = null) : Resources<T>(data = data)
    class Error<T>(
        data: T? = null,
        throwableError: Exception
    ) : Resources<T>(
        data = data,
        errorMessage = throwableError
    )
}
