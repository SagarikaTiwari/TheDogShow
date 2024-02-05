package com.sagarika.common

import java.lang.IndexOutOfBoundsException

sealed class Result<out T> {
    data class Success<out T>(val data: T) : Result<T>() // Status success and data of the result
    data class Error(val exception: Throwable) :
        Result<Nothing>() // Status Error an error message
}
