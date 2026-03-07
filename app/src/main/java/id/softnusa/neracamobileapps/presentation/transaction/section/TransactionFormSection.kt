package id.softnusa.neracamobileapps.presentation.transaction.section

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import id.softnusa.core.domain.util.formatRupiah
import id.softnusa.neracamobileapps.presentation.ui.component.AppButton
import id.softnusa.neracamobileapps.presentation.ui.component.AppTextField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionFormSection() {

    var selectedType by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf("") }

    var otherCategory by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf("") }
    var note by remember { mutableStateOf("") }

    var expandedType by remember { mutableStateOf(false) }
    var expandedCategory by remember { mutableStateOf(false) }

    val types = listOf("Pemasukan", "Pengeluaran")

    val categories = listOf(
        "Gaji",
        "Bayar Listrik",
        "Modal Awal",
        "Transport",
        "Lainnya"
    )

    val isFormValid =
        selectedType.isNotBlank() &&
                selectedCategory.isNotBlank() &&
                amount.isNotBlank()

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {

        /**
         * TYPE DROPDOWN
         */

        ExposedDropdownMenuBox(
            expanded = expandedType,
            onExpandedChange = { expandedType = !expandedType }
        ) {

            OutlinedTextField(
                value = selectedType,
                onValueChange = {},
                readOnly = true,
                label = { Text("Pilih tipe transaksi") },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(
                        expanded = expandedType
                    )
                },
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth()
            )

            ExposedDropdownMenu(
                expanded = expandedType,
                onDismissRequest = { expandedType = false }
            ) {

                types.forEach {

                    DropdownMenuItem(
                        text = { Text(it) },
                        onClick = {
                            selectedType = it
                            expandedType = false
                        }
                    )

                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))


        /**
         * CATEGORY DROPDOWN
         */

        ExposedDropdownMenuBox(
            expanded = expandedCategory,
            onExpandedChange = { expandedCategory = !expandedCategory }
        ) {

            OutlinedTextField(
                value = selectedCategory,
                onValueChange = {},
                readOnly = true,
                label = { Text("Pilih jenis transaksi") },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(
                        expanded = expandedCategory
                    )
                },
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth()
            )

            ExposedDropdownMenu(
                expanded = expandedCategory,
                onDismissRequest = { expandedCategory = false }
            ) {

                categories.forEach {

                    DropdownMenuItem(
                        text = { Text(it) },
                        onClick = {
                            selectedCategory = it
                            expandedCategory = false
                        }
                    )

                }
            }
        }


        /**
         * OTHER CATEGORY
         */

        AnimatedVisibility(
            visible = selectedCategory == "Lainnya"
        ) {

            Column {

                Spacer(modifier = Modifier.height(16.dp))

                AppTextField(
                    value = otherCategory,
                    onValueChange = { otherCategory = it },
                    label = "Jenis lainnya"
                )
            }
        }


        Spacer(modifier = Modifier.height(16.dp))


        /**
         * NOMINAL FIELD
         */

        AppTextField(
            value = if (amount.isEmpty()) "" else formatRupiah(amount.toLong()),
            onValueChange = {

                val cleanInput = it.replace("[^0-9]".toRegex(), "")
                amount = cleanInput

            },
            label = "Nominal",
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            )
        )


        Spacer(modifier = Modifier.height(16.dp))


        /**
         * NOTE
         */

        AppTextField(
            value = note,
            onValueChange = { note = it },
            label = "Catatan"
        )


        Spacer(modifier = Modifier.height(28.dp))


        /**
         * BUTTON
         */

        AppButton(
            text = "Simpan Transaksi",
            enabled = isFormValid,
            onClick = {

                val categoryPayload =
                    if (selectedCategory == "Lainnya")
                        otherCategory
                    else
                        selectedCategory

                val payload = mapOf(
                    "type" to selectedType,
                    "category" to categoryPayload,
                    "amount" to amount,
                    "note" to note
                )

                println(payload)
            }
        )


        Spacer(modifier = Modifier.height(24.dp))
    }
}