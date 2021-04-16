package com.zm.domain.util

sealed class Resource

class Success<T>(val data: T? = null): Resource()

class Loading<T>(data: T? = null): Resource()

class Failure(val error: Throwable? = null): Resource()
