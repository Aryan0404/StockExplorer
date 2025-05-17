// data/model/NetworkResult.kt
package com.example.stockexplorer.data.model

/**
 * A generic class that holds a value with its loading status.
 * @param <T>
 */
sealed class NetworkResult<out T> {
    data class Success<out T>(val data: T) : NetworkResult<T>()
    data class Error(val message: String) : NetworkResult<Nothing>()
    object Loading : NetworkResult<Nothing>()

    companion object {
        fun <T> success(data: T): NetworkResult<T> = Success(data)
        fun error(message: String): NetworkResult<Nothing> = Error(message)
        fun loading(): NetworkResult<Nothing> = Loading
    }
}

/**
 * Extension function to handle network results more elegantly
 */
inline fun <T, R> NetworkResult<T>.fold(
    onSuccess: (T) -> R,
    onError: (String) -> R,
    onLoading: () -> R
): R {
    return when (this) {
        is NetworkResult.Success -> onSuccess(data)
        is NetworkResult.Error -> onError(message)
        is NetworkResult.Loading -> onLoading()
    }
}