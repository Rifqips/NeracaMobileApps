package id.softnusa.neracamobileapps.presentation.navigation.bottomnav

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ReceiptLong
import androidx.compose.material.icons.automirrored.outlined.ReceiptLong
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Receipt
import androidx.compose.material.icons.outlined.AccountBalanceWallet
import androidx.compose.material.icons.outlined.BarChart
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Receipt
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(
    val route: String,
    val title: String,
    val icon: ImageVector,
    val selectedIcon: ImageVector
) {
    object Home : BottomNavItem(
        "home",
        "Beranda",
        Icons.Outlined.Home,
        Icons.Filled.Home
    )

    object Financial : BottomNavItem(
        "financial",
        "Keungan",
        Icons.Outlined.Receipt,
        Icons.Filled.Receipt
    )

    object Budget : BottomNavItem(
        "budget",
        "Monitoring",
        Icons.Outlined.BarChart,
        Icons.Filled.BarChart
    )

    object Profile : BottomNavItem(
        "profile",
        "Akun",
        Icons.Outlined.Person,
        Icons.Filled.Person
    )
}