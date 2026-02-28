package id.softnusa.neracamobileapps.presentation.ui.component

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun AppSnackbarHost(
    hostState: SnackbarHostState
) {
    SnackbarHost(
        hostState = hostState
    ) { data ->
        Snackbar(
            snackbarData = data,
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = Color.White
        )
    }
}