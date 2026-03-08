package id.softnusa.neracamobileapps.presentation.mainfeature.home.section

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.History
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import id.softnusa.neracamobileapps.presentation.navigation.Screen

@Composable
fun QuickActionSection(navController: NavHostController) {
    Surface(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .offset(y = (-28).dp),
        shape = RoundedCornerShape(20.dp),
        tonalElevation = 6.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {

            QuickActionItem(
                "Buat Transaksi",
                Icons.Default.Add
            ) {
                navController.navigate(Screen.Transaction.route)
            }

            QuickActionItem("Anggaran", Icons.Default.AccountBalance) {}

            QuickActionItem("Riwayat", Icons.Default.History) {
                navController.navigate(Screen.History.route)
            }
        }
    }
}

@Composable
private fun QuickActionItem(
    title: String,
    icon: ImageVector,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable { onClick() }
    ) {

        Box(
            modifier = Modifier
                .size(48.dp)
                .background(Color(0xFF7C4DFF), CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(icon, contentDescription = null, tint = Color.White)
        }

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = title,
            style = MaterialTheme.typography.bodySmall
        )
    }
}