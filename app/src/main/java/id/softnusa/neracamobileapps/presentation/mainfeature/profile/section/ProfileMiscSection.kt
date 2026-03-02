package id.softnusa.neracamobileapps.presentation.mainfeature.profile.section

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ProfileMiscSection() {
    ProfileMenuCard(
        title = "Lainnya",
        items = listOf(
            "Syarat & Ketentuan",
            "Kebijakan Privasi"
        )
    )
}
@Composable
fun ProfileMenuCard(
    title: String,
    items: List<String>
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        shape = MaterialTheme.shapes.large
    ) {
        Column(modifier = Modifier.padding(vertical = 8.dp)) {

            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)
            )

            items.forEach { item ->
                ProfileMenuItem(title = item)
            }
        }
    }
}

@Composable
fun ProfileMenuItem(title: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { }
            .padding(horizontal = 16.dp, vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.weight(1f)
        )

        Text(
            text = ">",
            style = MaterialTheme.typography.bodyMedium
        )
    }
}