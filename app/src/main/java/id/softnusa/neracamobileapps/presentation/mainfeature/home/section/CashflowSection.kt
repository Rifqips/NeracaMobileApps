package id.softnusa.neracamobileapps.presentation.mainfeature.home.section

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CashflowSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Arus Kas", style = MaterialTheme.typography.titleMedium)
            Text(
                "Lihat Semua",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.primary
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Surface(
            shape = RoundedCornerShape(16.dp),
            tonalElevation = 2.dp,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .height(160.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                CashBar("Jan", 120, 80)
                CashBar("Feb", 60, 140)
                CashBar("Mar", 90, 110)
                CashBar("Apr", 140, 70)
                CashBar("Mei", 100, 90)
                CashBar("Jun", 70, 130)
            }
        }
    }
}

@Composable
private fun CashBar(
    label: String,
    income: Int,
    expense: Int
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .width(24.dp)
                .height(income.dp)
                .background(Color(0xFF8BC34A), RoundedCornerShape(8.dp))
        )
        Box(
            modifier = Modifier
                .width(24.dp)
                .height(expense.dp)
                .background(Color(0xFFFF9800), RoundedCornerShape(8.dp))
        )

        Spacer(modifier = Modifier.height(6.dp))
        Text(label, style = MaterialTheme.typography.bodySmall)
    }
}