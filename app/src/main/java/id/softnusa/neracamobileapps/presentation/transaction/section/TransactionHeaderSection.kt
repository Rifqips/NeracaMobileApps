package id.softnusa.neracamobileapps.presentation.transaction.section

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun TransactionHeaderSection(
    onBackClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(220.dp)
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF8B5CF6),
                        Color(0xFF6D28D9)
                    )
                )
            )
            .padding(horizontal = 20.dp, vertical = 16.dp)
    ) {

        Column {

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.White,
                    modifier = Modifier
                        .size(26.dp)
                        .clickable { onBackClick() }
                )

                Spacer(modifier = Modifier.width(10.dp))

                Text(
                    text = "Buat Transaksi",
                    style = MaterialTheme.typography.headlineSmall,
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Catat pemasukan atau pengeluaran bisnis Anda",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White.copy(alpha = 0.9f)
            )
        }
    }
}