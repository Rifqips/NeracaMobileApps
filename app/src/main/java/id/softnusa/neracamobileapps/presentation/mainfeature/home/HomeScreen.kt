package id.softnusa.neracamobileapps.presentation.mainfeature.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import id.softnusa.neracamobileapps.presentation.mainfeature.home.section.*

@Composable
fun HomeScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
    ) {
        item {
            HomeHeaderSection()
        }

        item {
            QuickActionSection(navController)
        }

        item {
            IncomeExpenseSection()
        }

        item {
            RecentTransactionSection()
        }

        item {
            CashflowSection()
        }

        item {
            BudgetSection()
        }
    }
}