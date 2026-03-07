package id.softnusa.neracamobileapps.presentation.transaction

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import id.softnusa.neracamobileapps.presentation.transaction.section.TransactionFormSection
import id.softnusa.neracamobileapps.presentation.transaction.section.TransactionHeaderSection

@Composable
fun TransactionScreen(
    navController: NavHostController
) {

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        Column {

            TransactionHeaderSection(
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .offset(y = 140.dp),
            shape = RoundedCornerShape(20.dp),
            elevation = CardDefaults.cardElevation(6.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            )
        ) {

            Column(
                modifier = Modifier
                    .padding(24.dp)
                    .verticalScroll(rememberScrollState())
            ) {

                TransactionFormSection()

            }
        }
    }
}