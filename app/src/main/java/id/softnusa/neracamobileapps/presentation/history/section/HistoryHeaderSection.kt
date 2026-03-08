package id.softnusa.neracamobileapps.presentation.history.section

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun HistoryHeaderSection(
    onBackClick: () -> Unit
) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(170.dp)
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF8B5CF6),
                        Color(0xFF6D28D9)
                    )
                )
            )
            .padding(horizontal = 20.dp, vertical = 16.dp)
    ) {

        Column {

            /**
             * TOP BAR
             */
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier
                            .size(26.dp)
                            .clickable { onBackClick() }
                    )

                    Spacer(modifier = Modifier.width(10.dp))

                    Text(
                        text = "Riwayat Transaksi",
                        style = MaterialTheme.typography.titleLarge,
                        color = Color.White
                    )
                }

                /**
                 * FILTER ICON
                 */
                Icon(
                    imageVector = Icons.Default.FilterList,
                    contentDescription = "Filter",
                    tint = Color.White,
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { }
                )
            }

            Spacer(modifier = Modifier.height(26.dp))

            /**
             * SEARCH BAR
             */
            OutlinedTextField(
                value = "",
                onValueChange = {},
                placeholder = {
                    Text(
                        "Cari transaksi",
                        style = MaterialTheme.typography.bodySmall
                    )
                },
                trailingIcon = {
                    Icon(
                        Icons.Default.Search,
                        contentDescription = null,
                        modifier = Modifier.size(18.dp)
                    )
                },
                singleLine = true,
                shape = RoundedCornerShape(14.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(44.dp),
                textStyle = MaterialTheme.typography.bodySmall,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent
                )
            )
        }
    }
}