package id.softnusa.core.data.remote.model.request.prelogin

import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class RequestLoginDto(
    @SerialName("password")
    val password: String,
    @SerialName("username")
    val username: String
)