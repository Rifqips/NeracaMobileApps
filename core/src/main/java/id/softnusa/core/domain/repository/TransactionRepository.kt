package id.softnusa.core.domain.repository

import androidx.paging.PagingData
import id.softnusa.core.domain.model.request.transaction.RequestTransaction
import id.softnusa.core.domain.model.response.transaction.DataHistory
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


    fun getHistory(
        search: String
    ): Flow<PagingData<DataHistory>>


}