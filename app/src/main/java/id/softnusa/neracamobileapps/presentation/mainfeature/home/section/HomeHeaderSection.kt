package id.softnusa.neracamobileapps.presentation.mainfeature.home.section

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import id.softnusa.neracamobileapps.presentation.auth.AuthViewModel

@Composable
fun HomeHeaderSection(
    viewModel: AuthViewModel = hiltViewModel()
) {
    val username by viewModel.getUsername().collectAsState(initial = "Memuat...")
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(220.dp)
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF7C4DFF),
                        Color(0xFF9C6BFF)
                    )
                )
            )
            .padding(16.dp)
    ) {
        Column {
            Text(
                text = "Selamat Datang,",
                style = MaterialTheme.typography.bodySmall,
                color = Color.White
            )
            Text(
                text = username ?: "-",
                style = MaterialTheme.typography.titleMedium,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Total Saldo",
                style = MaterialTheme.typography.bodySmall,
                color = Color.White.copy(alpha = 0.9f)
            )

            Text(
                text = "Rp 1.320.000",
                style = MaterialTheme.typography.headlineMedium,
                color = Color.White
            )
        }
    }
}