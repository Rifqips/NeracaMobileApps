package id.softnusa.core.data.local.datastore

import kotlinx.coroutines.flow.Flow

interface ApplicationDataStore {


    suspend fun saveToken(token: String)

    fun getToken(): Flow<String?>

    suspend fun clearToken()

    fun isLoggedIn(): Flow<Boolean>

    suspend fun setOnboardingCompleted()

    fun isOnboardingCompleted(): Flow<Boolean>

    suspend fun saveUsername(username : String)
    fun getUsername() : Flow<String?>
}