package id.softnusa.neracamobileapps.presentation.mainfeature.monitoring.section

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import id.softnusa.neracamobileapps.presentation.mainfeature.home.section.CashBar

@Composable
fun MonitoringChartContent() {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp),
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