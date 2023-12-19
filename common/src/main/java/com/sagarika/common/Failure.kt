package com.sagarika.common

sealed class Failure {
    object NetworkConnection : Failure()
    object DataError : Failure()
    object ServerError : Failure()
}
