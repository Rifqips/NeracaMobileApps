package id.softnusa.neracamobileapps.presentation.onboarding

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import id.softnusa.neracamobileapps.presentation.ui.component.AppButton
import id.softnusa.neracamobileapps.presentation.ui.component.PagerIndicator
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingScreen(
    onFinished: () -> Unit,
    viewModel: OnboardingViewModel = hiltViewModel(),
) {

    val items = listOf(
        OnboardingItem(
            title = "Kelola Keuangan Lebih Mudah",
            description = "Pantau pemasukan dan pengeluaran dalam satu aplikasi."
        ),
        OnboardingItem(
            title = "Lihat Laporan Otomatis",
            description = "Dapatkan ringkasan neraca keuangan secara real-time."
        ),
        OnboardingItem(
            title = "Kontrol Finansial Masa Depan",
            description = "Buat perencanaan dan capai tujuan keuangan Anda."
        )
    )

    val pagerState = rememberPagerState(pageCount = { items.size })
    val scope = rememberCoroutineScope()

    Scaffold { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {

            HorizontalPager(
                state = pagerState,
                modifier = Modifier.weight(1f)
            ) { page ->
                OnboardingPage(item = items[page])
            }

            Spacer(modifier = Modifier.height(16.dp))

            PagerIndicator(
                pageCount = items.size,
                currentPage = pagerState.currentPage
            )

            Spacer(modifier = Modifier.height(24.dp))

            AppButton(
                text = if (pagerState.currentPage == items.lastIndex)
                    "Mulai"
                else
                    "Lanjut",
                onClick = {
                    if (pagerState.currentPage == items.lastIndex) {
                        viewModel.finishOnboarding()
                        onFinished()
                    } else {
                        scope.launch {
                            pagerState.animateScrollToPage(
                                pagerState.currentPage + 1
                            )
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}