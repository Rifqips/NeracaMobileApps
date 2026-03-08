package id.softnusa.core.data.remote.model.request.transaction

import androidx.annotation.Keep
import kotlinx.serialization.SerialName

@Keep
data class RequestTransactionDto(
    @SerialName("amount")
    val amount: Int,
    @SerialName("category")
    val category: String,
    @SerialName("note")
    val note: String,
    @SerialName("type")
    val type: String
)