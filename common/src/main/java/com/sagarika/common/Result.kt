package com.sagarika.common

import java.lang.IndexOutOfBoundsException

sealed class Result<out T> {
    data class Success<T>(val data: T) : Result<T>()
    data class Error(val exception: Throwable) :
        Result<Nothing>()
 }
