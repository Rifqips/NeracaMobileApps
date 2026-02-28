package id.softnusa.core.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BaseResponseDto<T>(

    @SerialName("success")
    val success: Boolean,

    @SerialName("message")
    val message: String,

    @SerialName("data")
    val data: T? = null
)