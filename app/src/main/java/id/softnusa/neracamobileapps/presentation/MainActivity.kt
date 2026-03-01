package id.softnusa.neracamobileapps.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import id.softnusa.neracamobileapps.presentation.navigation.AppNavGraph
import id.softnusa.neracamobileapps.presentation.ui.theme.NeracaTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            NeracaTheme {
                AppNavGraph()
            }
        }
    }
}