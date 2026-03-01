package id.softnusa.neracamobileapps.presentation.ui.component

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.SnackbarDuration
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * Non-blocking snackbar
 */

@Composable
fun AppSnackbarHost(
    hostState: SnackbarHostState
) {
    SnackbarHost(
        hostState = hostState
    ) { data ->
        Snackbar(
            snackbarData = data,
            containerColor = Color.Black,
            contentColor = Color.White
        )
    }
}


fun SnackbarHostState.showSnackbarAsync(
    scope: CoroutineScope,
    message: String,
    actionLabel: String? = null,
    duration: SnackbarDuration = SnackbarDuration.Short
) {
    scope.launch {
        showSnackbar(
            message = message,
            actionLabel = actionLabel,
            duration = duration
        )
    }
}