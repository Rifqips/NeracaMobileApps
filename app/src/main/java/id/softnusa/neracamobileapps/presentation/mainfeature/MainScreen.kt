package id.softnusa.neracamobileapps.presentation.mainfeature

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import id.softnusa.neracamobileapps.presentation.mainfeature.financial.FinancialScreen
import id.softnusa.neracamobileapps.presentation.mainfeature.home.HomeScreen
import id.softnusa.neracamobileapps.presentation.mainfeature.monitoring.MonitoringScreen
import id.softnusa.neracamobileapps.presentation.mainfeature.profile.ProfileScreen
import id.softnusa.neracamobileapps.presentation.navigation.bottomnav.BottomNavItem
import id.softnusa.neracamobileapps.presentation.navigation.bottomnav.NeracaBottomBar
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainScreen(
    navController: NavHostController
) {
    val bottomNavController = rememberNavController()

    Scaffold(
        bottomBar = {
            NeracaBottomBar(bottomNavController)
        }
    ) { padding ->
        NavHost(
            navController = bottomNavController,
            startDestination = BottomNavItem.Home.route,
            modifier = Modifier.padding(padding)
        ) {
            composable("home") { HomeScreen(navController = navController) }
            composable("financial") { FinancialScreen() }
            composable("budget") { MonitoringScreen() }
            composable("profile") { ProfileScreen(navController = navController) }
        }
    }
}