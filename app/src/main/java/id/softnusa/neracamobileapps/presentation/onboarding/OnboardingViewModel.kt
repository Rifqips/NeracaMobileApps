package id.softnusa.neracamobileapps.presentation.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.softnusa.core.data.local.datastore.ApplicationDataStore
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val dataStore: ApplicationDataStore
) : ViewModel() {

    fun finishOnboarding() {
        viewModelScope.launch {
            dataStore.setOnboardingCompleted()
        }
    }
}