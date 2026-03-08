package id.softnusa.neracamobileapps.presentation.history.section

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import id.softnusa.core.domain.util.formatRupiah
import id.softnusa.neracamobileapps.presentation.transaction.TransactionViewModel
@Composable
fun HistoryListSection(
    viewModel: TransactionViewModel = hiltViewModel()
) {

    val pagingItems = viewModel.historyPaging.collectAsLazyPagingItems()

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 12.dp)
    ) {

        items(pagingItems.itemCount) { index ->

            val item = pagingItems[index]

            item?.let {

                TransactionItem(
                    category = it.category,
                    note = it.note,
                    date = it.createdAt,
                    amount = it.amount,
                    type = it.type
                )
            }
        }
    }
}

@Composable
fun TransactionItem(
    category: String,
    note: String,
    date: String,
    amount: Int,
    type: String
) {

    val isIncome = type == "income"

    val color = if (isIncome) Color(0xFF4CAF50) else Color(0xFFE53935)

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        shape = RoundedCornerShape(16.dp),
        tonalElevation = 1.dp,
        color = Color(0xFFF5F5F5)
    ) {

        Row(
            modifier = Modifier.padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(color.copy(alpha = 0.15f), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "₹",
                    color = color,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {

                Text(
                    text = category,
                    style = MaterialTheme.typography.bodyMedium
                )

                Text(
                    text = note,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )
            }

            Column(
                horizontalAlignment = Alignment.End
            ) {

                Text(
                    text = if (isIncome)
                        "+ ${formatRupiah(amount.toLong())}"
                    else
                        "- ${formatRupiah(amount.toLong())}",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold,
                    color = color
                )

                Text(
                    text = date,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )
            }
        }
    }
}