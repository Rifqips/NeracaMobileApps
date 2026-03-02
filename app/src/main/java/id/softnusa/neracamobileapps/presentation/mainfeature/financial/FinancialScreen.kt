package id.softnusa.neracamobileapps.presentation.mainfeature.financial

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import id.softnusa.neracamobileapps.presentation.mainfeature.financial.section.FinancialCalendarCardSection
import id.softnusa.neracamobileapps.presentation.mainfeature.financial.section.FinancialHeaderSection
import id.softnusa.neracamobileapps.presentation.mainfeature.financial.section.FinancialInfoSection

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun FinancialScreen() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF2F4F7))
    ) {

        Box(
            modifier = Modifier.fillMaxWidth()
        ) {

            FinancialHeaderSection()

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(top = 160.dp),
                shape = RoundedCornerShape(24.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    FinancialCalendarCardSection()
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        FinancialInfoSection()
    }
}