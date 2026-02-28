package id.softnusa.core.data.remote.model.request.prelogin

import androidx.annotation.Keep
import kotlinx.serialization.SerialName

@Keep
data class RequestTokentDto(
    @SerialName("refreshToken")
    val refreshToken: String,
)

