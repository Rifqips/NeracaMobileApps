package id.softnusa.neracamobileapps.presentation.auth

import id.softnusa.core.domain.model.response.auth.ResponseLogin

data class LoginUiState(
    val isLoading: Boolean = false,
    val data: ResponseLogin? = null,
    val errorMessage: String? = null
)