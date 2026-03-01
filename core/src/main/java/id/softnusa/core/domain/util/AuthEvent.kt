package id.softnusa.core.domain.util

sealed class AuthEvent {
    object NavigateHome : AuthEvent()
    data class ShowSnackbar(val message: String) : AuthEvent()
}