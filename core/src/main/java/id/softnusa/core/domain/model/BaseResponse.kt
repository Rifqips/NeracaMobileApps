package id.softnusa.core.domain.model

data class BaseResponse<T>(
    val success: Boolean,
    val message: String,
    val data: T? = null
)