package id.softnusa.neracamobileapps.presentation.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.softnusa.core.domain.model.request.auth.RequestLogin
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import id.softnusa.core.domain.repository.AuthRepository
import id.softnusa.core.domain.util.Resource

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    fun login(username: String, password: String) {

        viewModelScope.launch {

            repository.login(
                RequestLogin(
                    username = username,
                    password = password
                )
            ).collect { result ->

                when (result) {

                    is Resource.Loading -> {
                        _uiState.value = LoginUiState(
                            isLoading = true
                        )
                    }

                    is Resource.Success -> {
                        _uiState.value = LoginUiState(
                            data = result.data
                        )
                    }

                    is Resource.Error -> {
                        _uiState.value = LoginUiState(
                            errorMessage = result.message
                        )
                    }
                }
            }
        }
    }
}