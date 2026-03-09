package id.softnusa.core.data.local.token

import id.softnusa.core.data.local.datastore.ApplicationDataStore
import kotlinx.coroutines.flow.first
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TokenManager @Inject constructor(
    private val dataStore: ApplicationDataStore
) {

    suspend fun getAccessToken(): String? {
        return dataStore.getToken().first()
    }

    suspend fun getRefreshToken(): String? {
        return dataStore.getRefreshToken().first()
    }

    suspend fun saveToken(
        access: String,
        refresh: String
    ) {
        dataStore.saveTokens(access, refresh)
    }

    suspend fun clear() {
        dataStore.clearToken()
    }
}