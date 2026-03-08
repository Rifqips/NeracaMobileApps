package id.softnusa.neracamobileapps.presentation.auth.login

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Checkbox
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import id.softnusa.core.domain.util.event.AuthEvent
import id.softnusa.neracamobileapps.R
import id.softnusa.neracamobileapps.presentation.ui.component.AppButton
import id.softnusa.neracamobileapps.presentation.ui.component.AppTextField
import id.softnusa.neracamobileapps.presentation.ui.theme.Primary
import id.softnusa.neracamobileapps.presentation.ui.theme.TextSecondary
import kotlinx.coroutines.launch
import id.softnusa.neracamobileapps.presentation.auth.AuthViewModel
import id.softnusa.neracamobileapps.presentation.ui.component.AppPasswordField
import id.softnusa.neracamobileapps.presentation.ui.component.AppSnackbarHost
import id.softnusa.neracamobileapps.presentation.ui.component.showSnackbarAsync

@Composable
fun LoginScreen(
    viewModel: AuthViewModel = hiltViewModel(),
    onLoginSuccess: () -> Unit,
    onNavigateToRegister: () -> Unit,
    onNavigateToForgotPassword: () -> Unit
) {

    val state by viewModel.uiState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    val loginTitle = stringResource(R.string.txt_login_title)
    val loginSubtitle = stringResource(R.string.txt_login_subtitle)
    val emailLabel = stringResource(R.string.txt_login_email)
    val passwordLabel = stringResource(R.string.txt_login_password)
    val forgotPasswordText = stringResource(R.string.txt_login_forgot_password)
    val loginButtonText = stringResource(R.string.txt_login_button)

    val permissionDeniedText =
        stringResource(R.string.txt_error_permission_denied)

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        viewModel.event.collect { event ->

            when (event) {

                is AuthEvent.NavigateHome -> {
                    onLoginSuccess()
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

    val notificationLauncher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestPermission()
        ) { granted ->
            if (!granted) {
                scope.launch {
                    snackbarHostState.showSnackbar(permissionDeniedText)
                }
            }
        }

    LaunchedEffect(Unit) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {

            val granted = ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED

            if (!granted) {
                notificationLauncher.launch(
                    Manifest.permission.POST_NOTIFICATIONS
                )
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
                text = loginTitle,
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = loginSubtitle,
                style = MaterialTheme.typography.bodyMedium,
                color = TextSecondary
            )

            Spacer(modifier = Modifier.height(32.dp))

            AppTextField(
                value = email,
                onValueChange = { email = it },
                label = emailLabel
            )

            Spacer(modifier = Modifier.height(16.dp))

            AppPasswordField(
                value = password,
                onValueChange = { password = it },
                label = passwordLabel
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = forgotPasswordText,
                    style = MaterialTheme.typography.bodySmall,
                    color = Primary,
                    modifier = Modifier.clickable {
                        onNavigateToForgotPassword()
                    }
                )
            }

            Spacer(modifier = Modifier.height(28.dp))

            AppButton(
                text = loginButtonText,
                isLoading = state.isLoading,
                enabled = email.isNotBlank() && password.isNotBlank(),
                onClick = {
                    viewModel.login(email, password)
                }
            )

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {

                Text(
                    text = "Belum punya akun?",
                    style = MaterialTheme.typography.bodySmall
                )

                Spacer(modifier = Modifier.width(6.dp))

                Text(
                    text = "Register",
                    style = MaterialTheme.typography.bodySmall,
                    color = Primary,
                    modifier = Modifier.clickable {
                        onNavigateToRegister()
                    }
                )
            }
        }
    }
}