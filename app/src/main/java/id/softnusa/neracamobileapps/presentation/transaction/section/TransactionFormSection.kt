package id.softnusa.neracamobileapps.presentation.transaction.section

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.*
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import id.softnusa.core.domain.util.formatRupiah
import id.softnusa.neracamobileapps.presentation.transaction.TransactionViewModel
import id.softnusa.neracamobileapps.presentation.ui.component.AppButton
import id.softnusa.neracamobileapps.presentation.ui.component.AppTextField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionFormSection(
    viewModel: TransactionViewModel = hiltViewModel()
) {

    val state by viewModel.uiState.collectAsState()

    var expandedType by remember { mutableStateOf(false) }
    var expandedCategory by remember { mutableStateOf(false) }

    val types = listOf("Pemasukan", "Pengeluaran")

    val categories = state.categories.map { it.name } + "Lainnya"

    val isFormValid =
        state.selectedType.isNotBlank() &&
                state.selectedCategory.isNotBlank() &&
                state.amount.isNotBlank()

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {

        AnimatedVisibility(state.isLoading) {

            LinearProgressIndicator(
                modifier = Modifier.fillMaxWidth()
            )

        }

        /**
         * TYPE
         */

        ExposedDropdownMenuBox(
            expanded = expandedType,
            onExpandedChange = { expandedType = !expandedType }
        ) {

            OutlinedTextField(
                value = state.selectedType,
                onValueChange = {},
                readOnly = true,
                label = { Text("Pilih tipe transaksi") },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expandedType)
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

                            expandedType = false
                            viewModel.selectType(it)

                        }
                    )
                }

            }
        }

        Spacer(Modifier.height(16.dp))

        /**
         * CATEGORY
         */

        ExposedDropdownMenuBox(
            expanded = expandedCategory,
            onExpandedChange = {

                if (state.selectedType.isNotBlank()) {
                    expandedCategory = !expandedCategory
                }

            }
        ) {

            OutlinedTextField(
                value = state.selectedCategory,
                onValueChange = {},
                readOnly = true,
                enabled = state.selectedType.isNotBlank(),
                label = { Text("Pilih kategori") },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expandedCategory)
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

                            expandedCategory = false
                            viewModel.selectCategory(it)

                        }
                    )
                }

            }
        }

        AnimatedVisibility(state.selectedCategory == "Lainnya") {

            Column {

                Spacer(Modifier.height(16.dp))

                AppTextField(
                    value = state.otherCategory,
                    onValueChange = viewModel::updateOtherCategory,
                    label = "Jenis lainnya"
                )

            }

        }

        Spacer(Modifier.height(16.dp))

        AppTextField(
            value = if (state.amount.isEmpty()) ""
            else formatRupiah(state.amount.toLong()),
            onValueChange = viewModel::updateAmount,
            label = "Nominal",
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            )
        )

        Spacer(Modifier.height(16.dp))

        AppTextField(
            value = state.note,
            onValueChange = viewModel::updateNote,
            label = "Catatan"
        )

        Spacer(Modifier.height(28.dp))

        AppButton(
            text = "Simpan Transaksi",
            enabled = isFormValid,
            onClick = {

                val categoryPayload =
                    if (state.selectedCategory == "Lainnya")
                        state.otherCategory
                    else
                        state.selectedCategory

                val payload = mapOf(
                    "type" to state.selectedType,
                    "category" to categoryPayload,
                    "amount" to state.amount,
                    "note" to state.note
                )

                println(payload)

            }
        )

        Spacer(Modifier.height(24.dp))

    }

}