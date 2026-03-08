package id.softnusa.core.data.mapper.transaction

import id.softnusa.core.data.remote.model.response.transaction.DataHistoryDto
import id.softnusa.core.data.remote.model.response.transaction.MetaHistoryDto
import id.softnusa.core.data.remote.model.response.transaction.ResponseHistoryDto
import id.softnusa.core.domain.model.response.transaction.DataHistory
import id.softnusa.core.domain.model.response.transaction.MetaHistory
import id.softnusa.core.domain.model.response.transaction.ResponseHistory

fun ResponseHistoryDto.toDomain(): ResponseHistory {
    return ResponseHistory(
        data = data.map { it.toDomain() },
        message = message,
        meta = meta.toDomain(),
        success = success
    )
}

fun DataHistoryDto.toDomain(): DataHistory {
    return DataHistory(
        amount = amount,
        category = category,
        createdAt = createdAt,
        id = id,
        note = note,
        type = type
    )
}

fun MetaHistoryDto.toDomain(): MetaHistory {
    return MetaHistory(
        page = page,
        pageCount = pageCount,
        total = total
    )
}