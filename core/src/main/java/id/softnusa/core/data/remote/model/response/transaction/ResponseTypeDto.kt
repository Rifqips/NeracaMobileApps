package id.softnusa.core.data.remote.model.response.transaction

import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class ResponseTypeDto(
  @SerialName("name")
  val name: String,
  @SerialName("type")
  val type: String
)