package id.softnusa.neracamobileapps.presentation.transaction

import id.softnusa.core.domain.model.response.transaction.ResponseType

data class TransactionUiState(

    val isLoading: Boolean = false,

    val selectedType: String = "",
    val selectedCategory: String = "",

    val otherCategory: String = "",
    val amount: String = "",
    val note: String = "",

    val categories: List<ResponseType> = emptyList()

)