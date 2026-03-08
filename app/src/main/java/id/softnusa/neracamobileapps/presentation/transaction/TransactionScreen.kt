package id.softnusa.neracamobileapps.presentation.transaction

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import id.softnusa.neracamobileapps.presentation.transaction.section.TransactionFormSection
import id.softnusa.neracamobileapps.presentation.transaction.section.TransactionHeaderSection
import id.softnusa.neracamobileapps.presentation.ui.component.AppSnackbarHost

@Composable
fun TransactionScreen(
    navController: NavHostController
) {

    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        snackbarHost = { AppSnackbarHost(snackbarHostState) }
    ) { padding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {

            Column {

                TransactionHeaderSection(
                    onBackClick = { navController.popBackStack() }
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
                        .imePadding()
                        .verticalScroll(rememberScrollState())
                ) {

                    TransactionFormSection(
                        snackbarHostState = snackbarHostState,
                        onSuccess = { navController.popBackStack() }
                    )

                }
            }
        }
    }
}