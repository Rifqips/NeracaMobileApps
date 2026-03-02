package id.softnusa.neracamobileapps.presentation.mainfeature.home.section

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun BudgetSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {

        Text(
            text = "Anggaran",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            BudgetCard(
                modifier = Modifier.weight(1f),
                title = "Pemasukan",
                amount = "Rp 600.000",
                bgColor = Color(0xFFE8F5E9),
                accent = Color(0xFF8BC34A)
            )
            BudgetCard(
                modifier = Modifier.weight(1f),
                title = "Pengeluaran",
                amount = "Rp 200.000",
                bgColor = Color(0xFFFFF3E0),
                accent = Color(0xFFFF9800)
            )
        }
    }
}

@Composable
fun BudgetCard(
    modifier: Modifier = Modifier,
    title: String,
    amount: String,
    bgColor: Color,
    accent: Color
) {
    Surface(
        modifier = modifier
            .height(160.dp),
        shape = RoundedCornerShape(20.dp),
        color = bgColor
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyMedium
            )
            Box(
                modifier = Modifier
                    .background(accent, RoundedCornerShape(12.dp))
                    .padding(horizontal = 12.dp, vertical = 6.dp)
            ) {
                Text(
                    text = amount,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
