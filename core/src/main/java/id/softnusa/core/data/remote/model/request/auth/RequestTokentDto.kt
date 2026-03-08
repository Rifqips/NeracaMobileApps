package id.softnusa.core.data.remote.model.request.auth

import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class RequestTokentDto(
    @SerialName("refreshToken")
    val refreshToken: String,
)

