package id.softnusa.core.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import id.softnusa.core.data.mapper.toDomainList
import id.softnusa.core.data.mapper.transaction.toDomain
import id.softnusa.core.data.mapper.transaction.toDto
import id.softnusa.core.data.remote.api.TransactionApi
import id.softnusa.core.data.remote.model.response.transaction.ResponseTypeDto
import id.softnusa.core.data.remote.paging.HistoryPagingSource
import id.softnusa.core.domain.model.request.transaction.RequestTransaction
import id.softnusa.core.domain.model.response.transaction.DataHistory
import id.softnusa.core.domain.model.response.transaction.ResponseType
import id.softnusa.core.domain.repository.TransactionRepository
import id.softnusa.core.domain.util.Resource
import id.softnusa.core.domain.util.SafeApiCall
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TransactionRepositoryImpl @Inject constructor(
    private val transactionApi: TransactionApi
)  : TransactionRepository{

    override fun getCategories(
        type: String,
        search: String?
    ): Flow<Resource<List<ResponseType>>> {

        return SafeApiCall.execute {

            val response = transactionApi.getCategories(type, search)

            val domain = response.toDomainList(ResponseTypeDto::toDomain)

            if (!domain.success || domain.data == null) {
                throw Exception(domain.message)
            }

            domain.data
        }
    }

    override fun createTransaction(
        request: RequestTransaction
    ): Flow<Resource<Unit>> {

        return SafeApiCall.execute {

            val response = transactionApi.createTransaction(
                request.toDto()
            )

            if (!response.success) {
                throw Exception(response.message)
            }

            Unit
        }
    }

    override fun getHistory(
        search: String
    ): Flow<PagingData<DataHistory>> {

        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                HistoryPagingSource(
                    api = transactionApi,
                    search = search
                )
            }
        ).flow
    }

}