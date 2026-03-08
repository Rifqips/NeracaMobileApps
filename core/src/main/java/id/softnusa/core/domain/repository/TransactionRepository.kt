package id.softnusa.core.domain.repository

import id.softnusa.core.domain.model.request.transaction.RequestTransaction
import id.softnusa.core.domain.model.response.transaction.ResponseType
import id.softnusa.core.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface TransactionRepository {

    fun getCategories(
        type: String,
        search: String? = null
    ): Flow<Resource<List<ResponseType>>>

    fun createTransaction(
        request: RequestTransaction
    ): Flow<Resource<Unit>>

}