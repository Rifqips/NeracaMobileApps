package id.softnusa.neracamobileapps.presentation.mainfeature.profile.section

import androidx.compose.runtime.Composable

@Composable
fun ProfileSupportSection() {
    ProfileMenuCard(
        title = "Bantuan",
        items = listOf(
            "Pusat Bantuan",
            "Pembaruan Terbaru"
        )
    )
}