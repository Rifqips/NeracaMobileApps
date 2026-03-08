package id.softnusa.core.domain.model.request.transaction


data class RequestTransaction(
    val amount: Int,
    val category: String,
    val note: String,
    val type: String
)