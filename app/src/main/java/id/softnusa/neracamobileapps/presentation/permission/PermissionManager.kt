package id.softnusa.neracamobileapps.presentation.permission

import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat

class PermissionManager(
    private val context: Context
) {

    fun isGranted(permission: AppPermission): Boolean {
        if (permission.permission.isEmpty()) return true

        return ContextCompat.checkSelfPermission(
            context,
            permission.permission
        ) == PackageManager.PERMISSION_GRANTED
    }
}