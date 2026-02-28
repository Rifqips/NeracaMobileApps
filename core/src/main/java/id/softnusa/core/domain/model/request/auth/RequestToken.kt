package id.softnusa.core.domain.model.request.auth

import androidx.annotation.Keep
import kotlinx.serialization.SerialName

@Keep
data class RequestToken(
    @SerialName("refreshToken")
    val refreshToken: String,
)

