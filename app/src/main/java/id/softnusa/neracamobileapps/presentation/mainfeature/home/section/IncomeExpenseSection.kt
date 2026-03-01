package id.softnusa.neracamobileapps.presentation.mainfeature.home.section

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp

@Composable
fun IncomeExpenseSection() {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        shape = RoundedCornerShape(16.dp),
        tonalElevation = 2.dp
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            IncomeExpenseItem(
                title = "Pemasukan",
                amount = "Rp 850.000",
                progress = 0.7f,
                color = Color(0xFF7C4DFF)
            )

            Spacer(modifier = Modifier.height(16.dp))

            IncomeExpenseItem(
                title = "Pengeluaran",
                amount = "Rp 420.000",
                progress = 0.5f,
                color = Color(0xFFFF9800)
            )
        }
    }
}

@Composable
private fun IncomeExpenseItem(
    title: String,
    amount: String,
    progress: Float,
    color: Color
) {
    Column {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = amount,
                style = MaterialTheme.typography.bodyMedium
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        LinearProgressIndicator(
            progress = progress,
            modifier = Modifier
                .fillMaxWidth()
                .height(10.dp),
            color = color,
            trackColor = color.copy(alpha = 0.2f),
            strokeCap = StrokeCap.Round
        )
    }
}