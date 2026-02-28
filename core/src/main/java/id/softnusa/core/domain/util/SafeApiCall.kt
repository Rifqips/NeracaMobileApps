package id.softnusa.core.domain.util

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

inline fun <T> safeApiCall(
    crossinline apiCall: suspend () -> T
): Flow<Resource<T>> = flow {

    emit(Resource.Loading)

    try {
        val result = apiCall()
        emit(Resource.Success(result))
    } catch (e: Exception) {
        emit(
            Resource.Error(
                message = e.message ?: "Unknown error",
                throwable = e
            )
        )
    }
}