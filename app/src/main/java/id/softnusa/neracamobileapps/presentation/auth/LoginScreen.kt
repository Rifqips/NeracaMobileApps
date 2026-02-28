package id.softnusa.neracamobileapps.presentation.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import id.softnusa.neracamobileapps.R
import id.softnusa.neracamobileapps.presentation.ui.component.AppButton
import id.softnusa.neracamobileapps.presentation.ui.component.AppSnackbarHost
import id.softnusa.neracamobileapps.presentation.ui.component.AppTextField
import id.softnusa.neracamobileapps.presentation.ui.component.AuthCard
import id.softnusa.neracamobileapps.presentation.ui.theme.*
import android.Manifest
import android.os.Build
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel()
) {

    val state by viewModel.uiState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    val loginTitle = stringResource(R.string.txt_login_title)
    val loginSubtitle = stringResource(R.string.txt_login_subtitle)
    val usernameLabel = stringResource(R.string.txt_login_username)
    val passwordLabel = stringResource(R.string.txt_login_password)
    val rememberMeText = stringResource(R.string.txt_login_remember_me)
    val forgotPasswordText = stringResource(R.string.txt_login_forgot_password)
    val loginButtonText = stringResource(R.string.txt_login_button)
    val loadingText = stringResource(R.string.txt_login_loading)
    val loginSuccessText = stringResource(R.string.txt_login_success)

    val invalidCredentialText =
        stringResource(R.string.txt_error_invalid_credential)
    val generalErrorText =
        stringResource(R.string.txt_error_general)
    val permissionDeniedText =
        stringResource(R.string.txt_error_permission_denied)

    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var rememberMe by remember { mutableStateOf(false) }

    LaunchedEffect(state.data) {
        state.data?.let {
            snackbarHostState.showSnackbar(loginSuccessText)
        }
    }

    LaunchedEffect(state.errorMessage) {
        state.errorMessage?.let { error ->
            val message = when (error) {
                "INVALID_CREDENTIAL" -> invalidCredentialText
                else -> generalErrorText
            }
            snackbarHostState.showSnackbar(message)
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

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(Background)
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp)
                    .background(
                        brush = Brush.verticalGradient(
                            listOf(PurpleStart, PurpleEnd)
                        )
                    )
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Spacer(modifier = Modifier.height(120.dp))

                AuthCard {

                    Text(
                        text = loginTitle,
                        style = MaterialTheme.typography.headlineLarge
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = loginSubtitle,
                        style = MaterialTheme.typography.bodyMedium,
                        color = TextSecondary
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    AppTextField(
                        value = username,
                        onValueChange = { username = it },
                        label = usernameLabel
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        label = { Text(passwordLabel) },
                        modifier = Modifier.fillMaxWidth(),
                        visualTransformation = PasswordVisualTransformation(),
                        shape = MaterialTheme.shapes.medium
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Checkbox(
                                checked = rememberMe,
                                onCheckedChange = { rememberMe = it }
                            )
                            Text(
                                text = rememberMeText,
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }

                        Spacer(modifier = Modifier.weight(1f))

                        Text(
                            text = forgotPasswordText,
                            style = MaterialTheme.typography.labelLarge,
                            color = Primary
                        )
                    }

                    Spacer(modifier = Modifier.height(18.dp))

                    AppButton(
                        text = if (state.isLoading)
                            loadingText
                        else
                            loginButtonText,
                        onClick = {
                            viewModel.login(username, password)
                        }
                    )
                }
            }
        }
    }
}