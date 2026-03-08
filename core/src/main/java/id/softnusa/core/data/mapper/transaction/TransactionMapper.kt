package id.softnusa.core.data.mapper.transaction

import id.softnusa.core.data.remote.model.response.transaction.ResponseTypeDto
import id.softnusa.core.domain.model.response.transaction.ResponseType

fun ResponseTypeDto.toDomain() : ResponseType = ResponseType(
    type = type,
    name = name
)