package id.softnusa.neracamobileapps.presentation.navigation

sealed class Screen(val route: String) {

    object Onboarding : Screen("onboarding")
    object Login : Screen("login")
    object Home : Screen("home")
    object Splash : Screen("splash")
    object Register : Screen("register")
    object ForgotPw : Screen("forgot_pw")
}