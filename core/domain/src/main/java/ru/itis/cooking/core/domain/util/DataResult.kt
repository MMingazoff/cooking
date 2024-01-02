package ru.itis.cooking.core.domain.util

sealed class DataResult<out T> {
    object Loading: DataResult<Nothing>()
    data class Error(val message: String): DataResult<Nothing>()
    data class Success<out T>(val data: T): DataResult<T>()
}
