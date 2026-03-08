package id.softnusa.core.data.remote.api

import id.softnusa.core.data.remote.model.BaseResponseDto
import id.softnusa.core.data.remote.model.response.transaction.ResponseTypeDto

interface TransactionApi {

    suspend fun getCategories(
        type: String,
        search: String? = null
    ): BaseResponseDto<List<ResponseTypeDto>>
}