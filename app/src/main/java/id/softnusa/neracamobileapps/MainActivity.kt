package id.softnusa.neracamobileapps

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dagger.hilt.android.AndroidEntryPoint
import id.softnusa.neracamobileapps.presentation.auth.LoginScreen
import id.softnusa.neracamobileapps.presentation.ui.theme.NeracaTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NeracaTheme {
                LoginScreen()
            }
        }
    }
}
