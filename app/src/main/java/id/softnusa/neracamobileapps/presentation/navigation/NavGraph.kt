package id.softnusa.neracamobileapps.presentation.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import id.softnusa.neracamobileapps.presentation.auth.forgot.ForgotPasswordScreen
import id.softnusa.neracamobileapps.presentation.auth.login.LoginScreen
import id.softnusa.neracamobileapps.presentation.auth.register.RegisterScreen
import id.softnusa.neracamobileapps.presentation.history.HistoryTransactionScreen
import id.softnusa.neracamobileapps.presentation.mainfeature.MainScreen
import id.softnusa.neracamobileapps.presentation.onboarding.OnboardingScreen
import id.softnusa.neracamobileapps.presentation.splash.SplashScreen
import id.softnusa.neracamobileapps.presentation.transaction.TransactionScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavGraph() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(Screen.Splash.route) {
            SplashScreen(
                onNavigate = { route ->
                    navController.navigate(route) {
                        popUpTo(Screen.Splash.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable(Screen.Onboarding.route) {
            OnboardingScreen(
                onFinished = {
                    navController.navigate(Screen.Login.route) {
                        popUpTo(Screen.Onboarding.route) { inclusive = true }
                    }
                }
            )
        }

        composable(Screen.Login.route) {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                },
                onNavigateToRegister = {
                    navController.navigate(Screen.Register.route)
                },
                onNavigateToForgotPassword = {
                    navController.navigate(Screen.ForgotPw.route)
                }
            )
        }

        composable(Screen.Register.route) {
            RegisterScreen(
                onRegisterSuccess = {
                    navController.navigate(Screen.Login.route)
                },
                onNavigateToLogin = {
                    navController.navigate(Screen.Login.route)
                }
            )
        }

        composable(Screen.ForgotPw.route) {
            ForgotPasswordScreen()
        }

        composable(Screen.Home.route) {
            MainScreen(navController = navController)
        }

        composable(Screen.Transaction.route) {
            TransactionScreen(navController = navController)
        }

        composable(Screen.History.route) {
            HistoryTransactionScreen(navController = navController)
        }

    }
}