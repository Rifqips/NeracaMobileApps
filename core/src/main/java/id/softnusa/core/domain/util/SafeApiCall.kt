package id.softnusa.core.domain.util

import id.softnusa.core.data.remote.model.BaseResponseDto
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.statement.bodyAsText
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

suspend fun <T> safeApiCall(
    apiCall: suspend () -> T
): Flow<Resource<T>> = flow {

    emit(Resource.Loading)

    try {
        val response = apiCall()
        emit(Resource.Success(response))

    } catch (e: ClientRequestException) {

        val errorBody = e.response.bodyAsText()

        emit(Resource.Error(errorBody))

    }catch (e: ClientRequestException) {
        val errorResponse: BaseResponseDto<Unit> =
            e.response.body()
        emit(Resource.Error(errorResponse.message))
    }
}