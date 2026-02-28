package id.softnusa.neracamobileapps.presentation.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import id.softnusa.neracamobileapps.presentation.ui.theme.SurfaceWhite

@Composable
fun AuthCard(
    content: @Composable () -> Unit
) {
    Card(
        modifier = Modifier.padding(horizontal = 24.dp),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = SurfaceWhite
        )
    ) {
        Column(modifier = Modifier.padding(24.dp)) {
            content()
        }
    }
}