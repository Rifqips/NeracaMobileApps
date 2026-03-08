package id.softnusa.neracamobileapps.presentation.permission

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext

@Composable
fun PermissionHandler(
    permission: AppPermission,
    onGranted: () -> Unit,
    onDenied: () -> Unit
) {

    val context = LocalContext.current
    val permissionManager = remember { PermissionManager(context) }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { granted ->
        if (granted) onGranted()
        else onDenied()
    }

    LaunchedEffect(Unit) {

        if (permission.permission.isEmpty()) {
            onGranted()
            return@LaunchedEffect
        }

        if (permissionManager.isGranted(permission)) {
            onGranted()
        } else {
            launcher.launch(permission.permission)
        }
    }
}