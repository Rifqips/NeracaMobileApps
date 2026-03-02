package id.softnusa.neracamobileapps.presentation.mainfeature.profile.section

import androidx.compose.runtime.Composable


@Composable
fun ProfileAccountSection() {
    ProfileMenuCard(
        title = "Akun",
        items = listOf(
            "Ubah Password",
            "Keamanan",
            "Notifikasi"
        )
    )
}