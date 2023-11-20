package com.sagarika.common

sealed class Response<T> {
    data class Success<T>(var data: T) : Response<T>()
    data class Error<T>(var message: String) : Response<T>()
}