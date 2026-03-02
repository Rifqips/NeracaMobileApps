package id.softnusa.neracamobileapps.presentation.mainfeature.profile

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import id.softnusa.core.domain.util.AuthEvent
import id.softnusa.neracamobileapps.presentation.mainfeature.profile.section.ProfileMiscSection
import id.softnusa.neracamobileapps.presentation.mainfeature.profile.section.LogoutSection
import id.softnusa.neracamobileapps.presentation.mainfeature.profile.section.ProfileAccountSection
import id.softnusa.neracamobileapps.presentation.mainfeature.profile.section.ProfileHeaderSection
import id.softnusa.neracamobileapps.presentation.mainfeature.profile.section.ProfileSupportSection
import id.softnusa.neracamobileapps.presentation.navigation.Screen

@Composable
fun ProfileScreen(
    navController: NavHostController,
    viewModel: ProfileViewModel = hiltViewModel()
) {

    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.event.collect { event ->
            when (event) {

                is AuthEvent.NavigateHome -> {
                    navController.navigate(Screen.Login.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            inclusive = true
                        }
                    }
                }

                is AuthEvent.ShowSnackbar -> {
                    Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .background(Color(0xFFF3F4F6))
        ) {

            ProfileHeaderSection()
            Spacer(modifier = Modifier.height(16.dp))

            ProfileAccountSection()
            Spacer(modifier = Modifier.height(16.dp))

            ProfileSupportSection()
            Spacer(modifier = Modifier.height(16.dp))

            ProfileMiscSection()
            Spacer(modifier = Modifier.height(16.dp))

            LogoutSection(
                onLogoutClick = {
                    viewModel.logout()
                }
            )

            Spacer(modifier = Modifier.height(100.dp))
        }

        if (uiState.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}