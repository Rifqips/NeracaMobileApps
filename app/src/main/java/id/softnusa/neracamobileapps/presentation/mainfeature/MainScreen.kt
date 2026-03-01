package id.softnusa.neracamobileapps.presentation.mainfeature

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import id.softnusa.neracamobileapps.presentation.mainfeature.budget.BudgetScreen
import id.softnusa.neracamobileapps.presentation.mainfeature.financial.FinancialScreen
import id.softnusa.neracamobileapps.presentation.mainfeature.home.HomeScreen
import id.softnusa.neracamobileapps.presentation.mainfeature.profile.ProfileScreen
import id.softnusa.neracamobileapps.presentation.mainfeature.wallet.WalletScreen
import id.softnusa.neracamobileapps.presentation.navigation.bottomnav.BottomNavItem
import id.softnusa.neracamobileapps.presentation.navigation.bottomnav.NeracaBottomBar
@Composable
fun MainScreen() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            NeracaBottomBar(navController)
        }
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = BottomNavItem.Home.route,
            modifier = Modifier.padding(padding)
        ) {
            composable("home") { HomeScreen() }
            composable("financial") { FinancialScreen() }
            composable("wallet") { WalletScreen() }
            composable("budget") { BudgetScreen() }
            composable("profile") { ProfileScreen() }
        }
    }
}