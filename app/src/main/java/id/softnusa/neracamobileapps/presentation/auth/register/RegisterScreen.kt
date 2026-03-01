package id.softnusa.neracamobileapps.presentation.auth.register

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import id.softnusa.core.domain.util.AuthEvent
import id.softnusa.core.domain.util.calculatePasswordStrength
import id.softnusa.core.domain.util.isValidEmail
import id.softnusa.neracamobileapps.R
import id.softnusa.neracamobileapps.presentation.auth.AuthViewModel
import id.softnusa.neracamobileapps.presentation.ui.component.AppButton
import id.softnusa.neracamobileapps.presentation.ui.component.AppPasswordField
import id.softnusa.neracamobileapps.presentation.ui.component.AppSnackbarHost
import id.softnusa.neracamobileapps.presentation.ui.component.AppTextField
import id.softnusa.neracamobileapps.presentation.ui.component.showSnackbarAsync
import id.softnusa.neracamobileapps.presentation.ui.theme.Primary
import id.softnusa.neracamobileapps.presentation.ui.theme.TextSecondary

@Composable
fun RegisterScreen(
    viewModel: AuthViewModel = hiltViewModel(),
    onRegisterSuccess: () -> Unit,
    onNavigateToLogin: () -> Unit
) {

    val state by viewModel.uiState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    val registerTitle = stringResource(R.string.txt_register_title)
    val registerSubtitle = stringResource(R.string.txt_register_subtitle)
    val emailLabel = stringResource(R.string.txt_register_username)
    val passwordLabel = stringResource(R.string.txt_register_password)
    val confirmPasswordLabel = stringResource(R.string.txt_register_confirm_password)
    val registerButtonText = stringResource(R.string.txt_register_button)
    val passwordNotMatchText = stringResource(R.string.txt_error_password_not_match)
    val invalidEmailText = stringResource(R.string.txt_error_invalid_email)

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    val passwordStrength = calculatePasswordStrength(password)

    val isEmailValid = isValidEmail(email)
    val isPasswordMatch = password == confirmPassword

    val isFormValid =
        email.isNotBlank() &&
                password.isNotBlank() &&
                confirmPassword.isNotBlank() &&
                isEmailValid &&
                isPasswordMatch

    val passwordFocusRequester = remember { FocusRequester() }
    val confirmFocusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    LaunchedEffect(Unit) {
        viewModel.event.collect { event ->
            when (event) {
                is AuthEvent.NavigateHome -> {
                    onRegisterSuccess()
                }
                is AuthEvent.ShowSnackbar -> {
                    snackbarHostState.showSnackbarAsync(
                        scope = scope,
                        message = event.message
                    )
                }
            }
        }
    }

    Scaffold(
        snackbarHost = { AppSnackbarHost(snackbarHostState) }
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 24.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = registerTitle,
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = registerSubtitle,
                style = MaterialTheme.typography.bodyMedium,
                color = TextSecondary
            )

            Spacer(modifier = Modifier.height(32.dp))

            AppTextField(
                value = email,
                onValueChange = { email = it },
                label = emailLabel,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = { passwordFocusRequester.requestFocus() }
                )
            )

            AnimatedVisibility(visible = email.isNotBlank() && !isEmailValid) {
                Text(
                    text = invalidEmailText,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Spacer(modifier = Modifier.height(16.dp))


            AppPasswordField(
                value = password,
                onValueChange = { password = it },
                label = passwordLabel,
                focusRequester = passwordFocusRequester,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = { confirmFocusRequester.requestFocus() }
                )
            )

            if (password.isNotBlank()) {

                val strengthText = when (passwordStrength) {
                    0,1 -> stringResource(R.string.txt_password_strength_weak)
                    2,3 -> stringResource(R.string.txt_password_strength_medium)
                    else -> stringResource(R.string.txt_password_strength_strong)
                }

                val strengthColor = when (passwordStrength) {
                    0,1 -> MaterialTheme.colorScheme.error
                    2,3 -> Color(0xFFFFA000)
                    else -> Color(0xFF2E7D32)
                }

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = strengthText,
                    color = strengthColor,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            AppPasswordField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                label = confirmPasswordLabel,
                focusRequester = confirmFocusRequester,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        focusManager.clearFocus()

                        if (isFormValid) {
                            viewModel.register(email, password)
                        }
                    }
                )
            )

            AnimatedVisibility(
                visible = confirmPassword.isNotBlank() && !isPasswordMatch
            ) {
                Text(
                    text = passwordNotMatchText,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Spacer(modifier = Modifier.height(28.dp))

            AppButton(
                text = registerButtonText,
                isLoading = state.isLoading,
                enabled = isFormValid,
                onClick = {
                    viewModel.register(email, password)
                }
            )

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(stringResource(R.string.txt_register_have_account))
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = stringResource(R.string.txt_register_login_here),
                    color = Primary,
                    modifier = Modifier.clickable {
                        onNavigateToLogin()
                    }
                )
            }
        }
    }
}