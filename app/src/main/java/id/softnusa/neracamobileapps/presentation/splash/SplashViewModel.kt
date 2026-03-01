package id.softnusa.neracamobileapps.presentation.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.softnusa.core.data.local.datastore.ApplicationDataStore
import id.softnusa.neracamobileapps.presentation.navigation.Screen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val dataStore: ApplicationDataStore
) : ViewModel() {

    private val _route = MutableStateFlow<String?>(null)
    val route: StateFlow<String?> = _route

    init {
        viewModelScope.launch {

            combine(
                dataStore.isOnboardingCompleted(),
                dataStore.isLoggedIn()
            ) { onboardingDone, loggedIn ->

                when {
                    !onboardingDone -> Screen.Onboarding.route
                    loggedIn -> Screen.Home.route
                    else -> Screen.Login.route
                }

            }.collect {
                _route.value = it
            }
        }
    }
}