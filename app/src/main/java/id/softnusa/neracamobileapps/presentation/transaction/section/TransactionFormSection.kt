package id.softnusa.neracamobileapps.presentation.transaction.section

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import id.softnusa.core.domain.util.event.TransactionEvent
import id.softnusa.core.domain.util.formatRupiah
import id.softnusa.neracamobileapps.presentation.transaction.TransactionViewModel
import id.softnusa.neracamobileapps.presentation.ui.component.AppButton
import id.softnusa.neracamobileapps.presentation.ui.component.AppTextField
import id.softnusa.neracamobileapps.presentation.ui.component.showSnackbarAsync

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionFormSection(
    viewModel: TransactionViewModel = hiltViewModel(),
    snackbarHostState: SnackbarHostState,
    onSuccess: () -> Unit
) {

    val state by viewModel.uiState.collectAsState()

    val scope = rememberCoroutineScope()

    var expandedType by remember { mutableStateOf(false) }
    var expandedCategory by remember { mutableStateOf(false) }

    val displayAmount = remember(state.amount) {
        if (state.amount.isEmpty()) ""
        else formatRupiah(state.amount.toLong())
    }

    val types = listOf("Pemasukan", "Pengeluaran")

    val categories = state.categories.map { it.name } + "Lainnya"

    val isFormValid = state.selectedType.isNotBlank() &&
            state.amount.isNotBlank() &&
            if (state.selectedCategory == "Lainnya") {
                state.otherCategory.isNotBlank()
            } else {
                state.selectedCategory.isNotBlank()
            }

    /**
     * EVENT LISTENER
     */

    LaunchedEffect(Unit) {

        viewModel.event.collect { event ->

            when (event) {

                is TransactionEvent.ShowSnackbar -> {

                    snackbarHostState.showSnackbarAsync(
                        scope = scope,
                        message = event.message
                    )

                }

                is TransactionEvent.TransactionCreated -> {
                    onSuccess()
                }
            }

        }
    }


    Column(
        modifier = Modifier
            .fillMaxWidth()
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
            value = displayAmount,
            onValueChange = { input ->

                val clean = input.filter { it.isDigit() }

                viewModel.updateAmount(clean)

            },
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
            isLoading = state.isLoading,
            enabled = isFormValid,
            onClick = {
                viewModel.createTransaction()
            }
        )

        Spacer(Modifier.height(24.dp))

    }

}