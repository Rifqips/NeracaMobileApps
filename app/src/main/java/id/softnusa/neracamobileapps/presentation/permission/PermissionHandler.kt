package id.softnusa.neracamobileapps.presentation.permission

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.*

@Composable
fun PermissionHandler(
    permission: AppPermission,
    onGranted: () -> Unit,
    onDenied: () -> Unit
) {

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { granted ->
        if (granted) onGranted()
        else onDenied()
    }

    LaunchedEffect(Unit) {
        if (permission.permission.isNotEmpty()) {
            launcher.launch(permission.permission)
        } else {
            onGranted()
        }
    }
}