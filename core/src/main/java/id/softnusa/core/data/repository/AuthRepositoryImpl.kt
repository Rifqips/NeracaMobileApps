package id.softnusa.core.data.repository

import id.softnusa.core.data.local.datastore.ApplicationDataStore
import id.softnusa.core.data.mapper.auth.toDomain
import id.softnusa.core.data.mapper.auth.toDto
import id.softnusa.core.data.mapper.toDomain
import id.softnusa.core.data.remote.api.AuthApi
import id.softnusa.core.domain.model.request.auth.RequestLogin
import id.softnusa.core.domain.model.request.auth.RequestLogout
import id.softnusa.core.domain.model.response.auth.ResponseLogin
import id.softnusa.core.domain.repository.AuthRepository
import id.softnusa.core.domain.util.Resource
import id.softnusa.core.domain.util.SafeApiCall
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authApi: AuthApi,
    private val dataStore: ApplicationDataStore
) : AuthRepository {

    override fun login(
        request: RequestLogin
    ): Flow<Resource<ResponseLogin>> {

        return SafeApiCall.execute {

            val response = authApi.login(request.toDto())
            val domain = response.toDomain { it.toDomain() }
            val username = request.username


            if (!domain.success || domain.data == null) {
                throw Exception(domain.message)
            }

            CoroutineScope(Dispatchers.IO).launch {
                dataStore.saveTokens(
                    domain.data.accessToken,
                    domain.data.refreshToken
                )
                dataStore.saveUsername(username)
            }

            domain.data
        }
    }

    override fun register(
        request: RequestLogin
    ): Flow<Resource<ResponseLogin>> {

        return SafeApiCall.execute {

            val response = authApi.register(request.toDto())
            val domain = response.toDomain { it.toDomain() }

            if (!domain.success || domain.data == null) {
                throw Exception(domain.message)
            }
            domain.data
        }
    }

    override fun logout(): Flow<Resource<ResponseLogin?>> {
        return SafeApiCall.execute {

            val token = dataStore.getToken().first() ?: ""
            val request = RequestLogout(refreshToken = token)
            val response = authApi.logout(request.toDto())
            val domain = response.toDomain { it.toDomain() }

            if (!domain.success) {
                throw Exception(domain.message)
            }

            dataStore.clearToken()

            domain.data
        }
    }

    override fun getUsername(): Flow<String?> {
        return dataStore.getUsername()
    }
}