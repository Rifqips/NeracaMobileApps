package id.softnusa.neracamobileapps.presentation.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.softnusa.core.domain.model.request.auth.RequestLogin
import id.softnusa.core.domain.repository.AuthRepository
import id.softnusa.core.domain.util.event.AuthEvent
import id.softnusa.core.domain.util.Resource
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(AuthUiState())
    val uiState = _uiState.asStateFlow()

    private val _event = Channel<AuthEvent>()
    val event = _event.receiveAsFlow()

    fun login(email: String, password: String) {

        viewModelScope.launch {

            repository.login(RequestLogin(password, email))
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

                            _event.send(AuthEvent.NavigateHome)
                        }

                        is Resource.Error -> {
                            _uiState.value = _uiState.value.copy(
                                isLoading = false
                            )

                            _event.send(
                                AuthEvent.ShowSnackbar(result.message)
                            )
                        }
                    }
                }
        }
    }

    fun register(email: String, password: String) {

        viewModelScope.launch {

            repository.register(RequestLogin(password, email))
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

                            _event.send(AuthEvent.NavigateHome)
                        }

                        is Resource.Error -> {
                            _uiState.value = _uiState.value.copy(
                                isLoading = false
                            )

                            _event.send(
                                AuthEvent.ShowSnackbar(result.message)
                            )
                        }
                    }
                }
        }
    }
}