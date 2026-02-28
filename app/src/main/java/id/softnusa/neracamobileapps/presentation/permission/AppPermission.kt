package id.softnusa.neracamobileapps.presentation.permission

import android.Manifest
import android.os.Build

sealed class AppPermission(val permission: String) {

    object Camera : AppPermission(Manifest.permission.CAMERA)

    object Notification : AppPermission(
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
            Manifest.permission.POST_NOTIFICATIONS
        else
            ""
    )

    object StorageRead : AppPermission(
        Manifest.permission.READ_EXTERNAL_STORAGE
    )

    object StorageWrite : AppPermission(
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    )
}