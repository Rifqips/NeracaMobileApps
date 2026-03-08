package id.softnusa.core.data.mapper


import id.softnusa.core.data.remote.model.BaseResponseDto
import id.softnusa.core.domain.model.BaseResponse

fun <T, R> BaseResponseDto<T>.toDomain(
    transform: (T) -> R
): BaseResponse<R> {
    return BaseResponse(
        success = success,
        message = message,
        data = data?.let { transform(it) }
    )
}

fun <T, R> BaseResponseDto<List<T>>.toDomainList(
    transform: (T) -> R
): BaseResponse<List<R>> {
    return BaseResponse(
        success = success,
        message = message,
        data = data?.map(transform)
    )
}