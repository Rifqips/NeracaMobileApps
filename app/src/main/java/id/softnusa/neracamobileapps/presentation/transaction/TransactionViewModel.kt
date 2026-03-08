package id.softnusa.neracamobileapps.presentation.transaction

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.softnusa.core.domain.model.request.transaction.RequestTransaction
import id.softnusa.core.domain.repository.TransactionRepository
import id.softnusa.core.domain.util.Resource
import id.softnusa.core.domain.util.event.TransactionEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransactionViewModel @Inject constructor(
    private val repository: TransactionRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(TransactionUiState())
    val uiState = _uiState.asStateFlow()

    private val _event = Channel<TransactionEvent>()
    val event = _event.receiveAsFlow()

    private val typeMap = mapOf(
        "Pemasukan" to "income",
        "Pengeluaran" to "expense"
    )

    fun selectType(type: String) {

        _uiState.value = _uiState.value.copy(
            selectedType = type,
            selectedCategory = ""
        )

        val apiType = typeMap[type] ?: return

        getCategories(apiType)

    }

    fun selectCategory(category: String) {

        _uiState.value = _uiState.value.copy(
            selectedCategory = category
        )

    }

    fun updateOtherCategory(value: String) {

        _uiState.value = _uiState.value.copy(
            otherCategory = value
        )

    }

    fun updateAmount(value: String) {

        val clean = value.filter { it.isDigit() }

        _uiState.value = _uiState.value.copy(
            amount = clean
        )
    }

    fun updateNote(value: String) {

        _uiState.value = _uiState.value.copy(
            note = value
        )

    }

    private fun getCategories(type: String) {

        viewModelScope.launch {

            repository.getCategories(type)
                .collect { result ->

                    when (result) {

                        is Resource.Loading -> {

                            _uiState.value = _uiState.value.copy(
                                isLoading = true
                            )

                        }

                        is Resource.Success -> {

                            _uiState.value = _uiState.value.copy(
                                isLoading = false,
                                categories = result.data
                            )

                        }

                        is Resource.Error -> {

                            _uiState.value = _uiState.value.copy(
                                isLoading = false
                            )

                            _event.send(
                                TransactionEvent.ShowSnackbar(result.message)
                            )

                        }
                    }
                }
        }
    }

    fun createTransaction() {

        viewModelScope.launch {

            val categoryPayload =
                if (_uiState.value.selectedCategory == "Lainnya")
                    _uiState.value.otherCategory
                else
                    _uiState.value.selectedCategory

            val apiType = typeMap[_uiState.value.selectedType]

            val request = RequestTransaction(
                type = apiType.orEmpty(),
                category = categoryPayload,
                amount = _uiState.value.amount.toInt(),
                note = _uiState.value.note
            )

            repository.createTransaction(request)
                .collect { result ->
                    when (result) {

                        is Resource.Loading -> {

                            _uiState.value = _uiState.value.copy(
                                isLoading = true
                            )

                        }

                        is Resource.Success -> {

                            _uiState.value = _uiState.value.copy(
                                isLoading = false
                            )

                            _event.send(
                                TransactionEvent.TransactionCreated
                            )

                        }

                        is Resource.Error -> {

                            _uiState.value = _uiState.value.copy(
                                isLoading = false
                            )

                            _event.send(
                                TransactionEvent.ShowSnackbar(result.message)
                            )

                        }
                    }
                }
        }
    }

}