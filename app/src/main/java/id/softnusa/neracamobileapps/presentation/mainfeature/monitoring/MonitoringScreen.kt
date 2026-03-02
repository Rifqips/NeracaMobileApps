package id.softnusa.neracamobileapps.presentation.mainfeature.monitoring

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import id.softnusa.neracamobileapps.presentation.mainfeature.monitoring.section.MonitoringChartContent
import id.softnusa.neracamobileapps.presentation.mainfeature.monitoring.section.MonitoringHeaderSection
import id.softnusa.neracamobileapps.presentation.mainfeature.monitoring.section.MonitoringSummarySection

@Composable
fun MonitoringScreen() {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF2F4F7))
    ) {

        Column {
            MonitoringHeaderSection()
            Spacer(modifier = Modifier.height(100.dp))
        }

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .offset(y = 160.dp),
            shape = RoundedCornerShape(24.dp)
        ) {

            Column(
                modifier = Modifier.padding(16.dp)
            ) {

                Text(
                    text = "Performa Bulanan",
                    style = MaterialTheme.typography.titleMedium
                )

                Spacer(modifier = Modifier.height(16.dp))

                MonitoringChartContent()
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .offset(y = 420.dp)
        ) {

            MonitoringSummarySection()
        }
    }
}