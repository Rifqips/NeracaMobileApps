package id.softnusa.neracamobileapps.presentation.history.section

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun HistoryListSection() {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 12.dp)
    ) {

        TransactionItem(
            "Setoran",
            "18 Nov • 21:16",
            "Rp 20.000",
            Color(0xFF8BC34A)
        )

        TransactionItem(
            "Pembayaran Adobe",
            "10 Nov • 01:16",
            "Rp 12.000",
            Color(0xFFFF9800)
        )

        TransactionItem(
            "Transfer Bank",
            "08 Nov • 11:20",
            "Rp 250.000",
            Color(0xFF2196F3)
        )

        TransactionItem(
            "Belanja Kantor",
            "06 Nov • 14:10",
            "Rp 90.000",
            Color(0xFFE91E63)
        )
    }
}

@Composable
fun TransactionItem(
    title: String,
    date: String,
    amount: String,
    color: Color
) {

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        shape = RoundedCornerShape(16.dp),
        tonalElevation = 1.dp
    ) {

        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(color.copy(alpha = 0.2f), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text("₹", color = color, fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {

                Text(
                    text = title,
                    style = MaterialTheme.typography.bodyMedium
                )

                Text(
                    text = date,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )
            }

            Text(
                text = amount,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold
            )
        }
    }
}