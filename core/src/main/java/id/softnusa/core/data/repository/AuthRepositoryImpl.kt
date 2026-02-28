package id.softnusa.core.data.repository

import id.softnusa.core.data.local.datastore.ApplicationDataStore
import id.softnusa.core.data.mapper.auth.toDomain
import id.softnusa.core.data.mapper.auth.toDto
import id.softnusa.core.data.mapper.toDomain
import id.softnusa.core.data.remote.api.AuthApi
import id.softnusa.core.domain.model.request.auth.RequestLogin
import id.softnusa.core.domain.model.response.auth.ResponseLogin
import id.softnusa.core.domain.repository.AuthRepository
import id.softnusa.core.domain.util.Resource
import id.softnusa.core.domain.util.safeApiCall
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authApi: AuthApi,
    private val tokenDataStore: ApplicationDataStore
) : AuthRepository {

    override fun login(
        request: RequestLogin
    ): Flow<Resource<ResponseLogin>> {

        return safeApiCall {

            val response = authApi.login(request.toDto())

            val domain = response.toDomain { it.toDomain() }

            if (!domain.success) {
                throw Exception(domain.message)
            }

            val data = domain.data
                ?: throw Exception("Data is null")

            tokenDataStore.saveToken(data.accessToken)

            data
        }
    }
}