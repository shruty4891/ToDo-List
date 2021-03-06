package com.shruti.demoproject.repository

sealed class UseCaseResult<T : Any> {
    class Success<T : Any>(val data: T) : UseCaseResult<T>()
    class Error<T : Any>(val exception: Throwable) : UseCaseResult<T>()
}