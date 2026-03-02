package id.softnusa.neracamobileapps.presentation.mainfeature.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.softnusa.core.domain.repository.AuthRepository
import id.softnusa.core.domain.util.AuthEvent
import id.softnusa.core.domain.util.Resource
import id.softnusa.neracamobileapps.presentation.auth.AuthUiState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel  @Inject constructor(
    private val repository: AuthRepository
): ViewModel() {

    private val _uiState = MutableStateFlow(AuthUiState())
    val uiState = _uiState.asStateFlow()

    private val _event = Channel<AuthEvent>()
    val event = _event.receiveAsFlow()

    fun logout() {

        viewModelScope.launch {
            repository.logout()
                .collect { result ->
                    when (result) {

                        is Resource.Loading -> {
                            _uiState.value = _uiState.value.copy(
                                isLoading = true
                            )
                        }

                        is Resource.Success -> {
                            _uiState.value = _uiState.value.copy(isLoading = false)
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