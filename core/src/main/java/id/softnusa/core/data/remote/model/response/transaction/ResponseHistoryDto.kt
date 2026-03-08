package id.softnusa.core.data.remote.model.response.transaction

import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class ResponseHistoryDto(
    @SerialName("data")
    val `data`: List<DataHistoryDto>,
    @SerialName("message")
    val message: String,
    @SerialName("meta")
    val meta: MetaHistoryDto,
    @SerialName("success")
    val success: Boolean
)


@Keep
@Serializable
data class MetaHistoryDto(
  @SerialName("page")
  val page: Int,
  @SerialName("pageCount")
  val pageCount: Int,
  @SerialName("total")
  val total: Int
)


@Keep
@Serializable
data class DataHistoryDto(
  @SerialName("amount")
  val amount: Int,
  @SerialName("category")
  val category: String,
  @SerialName("created_at")
  val createdAt: String,
  @SerialName("id")
  val id: String,
  @SerialName("note")
  val note: String,
  @SerialName("type")
  val type: String
)