package id.softnusa.neracamobileapps.presentation.mainfeature.profile.section

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import id.softnusa.neracamobileapps.presentation.auth.AuthViewModel

@Composable
fun ProfileHeaderSection(
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
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            ProfileAvatar()

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = username ?: "-",
                style = MaterialTheme.typography.headlineSmall,
                color = Color.White
            )
        }
    }
}

@Composable
fun ProfileAvatar() {
    Box(
        modifier = Modifier
            .size(90.dp)
            .background(Color.White, CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = Icons.Default.AccountCircle,
            contentDescription = "Profile",
            tint = Color(0xFF7C4DFF),
            modifier = Modifier.size(70.dp)
        )
    }
}