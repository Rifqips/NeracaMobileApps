package id.softnusa.core.data.local.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ApplicationDataStoreImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : ApplicationDataStore {

    companion object {
        private val TOKEN_KEY = stringPreferencesKey("auth_token")

        private val ONBOARDING_KEY =
            booleanPreferencesKey("onboarding_completed")
    }

    override suspend fun saveToken(token: String) {
        dataStore.edit { preferences ->
            preferences[TOKEN_KEY] = token
        }
    }

    override fun getToken(): Flow<String?> {
        return dataStore.data.map { preferences ->
            preferences[TOKEN_KEY]
        }
    }

    override suspend fun clearToken() {
        dataStore.edit { preferences ->
            preferences.remove(TOKEN_KEY)
        }
    }

    override fun isLoggedIn(): Flow<Boolean> {
        return dataStore.data.map { preferences ->
            preferences[TOKEN_KEY] != null
        }
    }

    override suspend fun setOnboardingCompleted() {
        dataStore.edit { prefs ->
            prefs[ONBOARDING_KEY] = true
        }
    }

    override fun isOnboardingCompleted(): Flow<Boolean> {
        return dataStore.data.map { prefs ->
            prefs[ONBOARDING_KEY] ?: false
        }
    }
}