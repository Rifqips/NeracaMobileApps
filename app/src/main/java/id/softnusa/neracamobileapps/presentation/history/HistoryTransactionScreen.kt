package id.softnusa.neracamobileapps.presentation.history

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import id.softnusa.neracamobileapps.presentation.history.section.HistoryHeaderSection
import id.softnusa.neracamobileapps.presentation.history.section.HistoryListSection
import id.softnusa.neracamobileapps.presentation.transaction.TransactionViewModel
import id.softnusa.neracamobileapps.presentation.ui.component.AppSnackbarHost

@Composable
fun HistoryTransactionScreen(
    navController: NavHostController
) {

    val snackbarHostState = remember { SnackbarHostState() }
    val viewModel: TransactionViewModel = hiltViewModel()
    val search by viewModel.search.collectAsState()

    Scaffold(
        snackbarHost = { AppSnackbarHost(snackbarHostState) }
    ) { padding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {

            Column {
                HistoryHeaderSection(
                    search = search,
                    onSearchChange = viewModel::updateSearch,
                    onBackClick = { navController.popBackStack() }
                )
                HistoryListSection()
            }
        }
    }
}