package id.softnusa.core.data.mapper.transaction

import id.softnusa.core.data.remote.model.request.transaction.RequestTransactionDto
import id.softnusa.core.data.remote.model.response.transaction.ResponseTypeDto
import id.softnusa.core.domain.model.request.transaction.RequestTransaction
import id.softnusa.core.domain.model.response.transaction.ResponseType


fun RequestTransaction.toDto() : RequestTransactionDto = RequestTransactionDto(
    amount = amount,
    category = category,
    note = note,
    type = type,

)
fun ResponseTypeDto.toDomain() : ResponseType = ResponseType(
    type = type,
    name = name
)