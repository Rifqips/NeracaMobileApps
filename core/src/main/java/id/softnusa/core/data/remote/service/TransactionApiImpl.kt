package id.softnusa.core.data.remote.service

import id.softnusa.core.data.local.datastore.ApplicationDataStore
import id.softnusa.core.data.remote.api.TransactionApi
import id.softnusa.core.data.remote.model.BaseResponseDto
import id.softnusa.core.data.remote.model.request.transaction.RequestTransactionDto
import id.softnusa.core.data.remote.model.response.transaction.ResponseHistoryDto
import id.softnusa.core.data.remote.model.response.transaction.ResponseTypeDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class TransactionApiImpl @Inject constructor(
    private val client: HttpClient,
    private val tokenDataStore: ApplicationDataStore
)  : TransactionApi {

    override suspend fun getCategories(
        type: String,
        search: String?
    ): BaseResponseDto<List<ResponseTypeDto>> {

        val token = tokenDataStore.getToken().first()

        return client.get("api/v1/categories/$type") {

            header("Authorization", "Bearer $token")

            search?.let {
                parameter("search", it)
            }

        }.body()
    }

    override suspend fun createTransaction(
        request: RequestTransactionDto
    ): BaseResponseDto<Unit> {

        val token = tokenDataStore.getToken().first()

        return client.post("api/v1/transactions") {

            header("Authorization", "Bearer $token")
            contentType(ContentType.Application.Json)

            setBody(request)

        }.body()
    }

    override suspend fun getHistory(
        page: Int,
        size: Int,
        search: String
    ): ResponseHistoryDto {

        val token = tokenDataStore.getToken().first()

        return client.get("api/v1/transactions") {

            header("Authorization", "Bearer $token")

            parameter("page", page)
            parameter("size", size)

            if (search.isNotBlank()) {
                parameter("search", search)
            }

        }.body()
    }
}