package id.softnusa.core.domain.model.response.transaction

data class ResponseHistory(
    val `data`: List<DataHistory>,
    val message: String,
    val meta: MetaHistory,
    val success: Boolean
)



data class MetaHistory(
  val page: Int,
  val pageCount: Int,
  val total: Int
)

data class DataHistory(
  val amount: Int,
  val category: String,
  val createdAt: String,
  val id: String,
  val note: String,
  val type: String
)