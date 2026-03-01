package id.softnusa.core.domain.util

import io.ktor.client.plugins.*
import io.ktor.client.statement.bodyAsText
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

object SafeApiCall {

    inline fun <T> execute(
        crossinline apiCall: suspend () -> T
    ): Flow<Resource<T>> = flow {

        emit(Resource.Loading)

        try {

            val response = apiCall()
            emit(Resource.Success(response))

        } catch (e: ClientRequestException) {

            val message = parseError(e.response.bodyAsText())
            emit(Resource.Error(message))

        } catch (e: ServerResponseException) {

            emit(Resource.Error("Server error"))

        } catch (e: Exception) {

            emit(Resource.Error(e.message ?: "Unknown error"))
        }
    }

    fun parseError(json: String): String {
        return try {
            val jsonObject = Json.parseToJsonElement(json).jsonObject
            jsonObject["message"]?.jsonPrimitive?.content
                ?: "Request error"
        } catch (e: Exception) {
            "Request error"
        }
    }
}