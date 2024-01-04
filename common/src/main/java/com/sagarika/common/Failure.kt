package com.sagarika.common

sealed class Failure {
    data object DataError : Failure()
    data object ServerError : Failure()
}
