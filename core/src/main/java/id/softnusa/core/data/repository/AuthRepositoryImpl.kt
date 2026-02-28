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
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authApi: AuthApi,
    private val tokenDataStore: ApplicationDataStore
) : AuthRepository {

    override fun login(
        request: RequestLogin
    ): Flow<Resource<ResponseLogin>> = flow {

        emit(Resource.Loading)

        try {

            val response = authApi.login(request.toDto())

            val domain = response.toDomain { it.toDomain() }

            if (domain.success && domain.data != null) {

                tokenDataStore.saveToken(domain.data.accessToken)

                emit(Resource.Success(domain.data))

            } else {

                emit(Resource.Error(domain.message))
            }

        } catch (e: Exception) {

            emit(Resource.Error(e.message ?: "Something went wrong"))
        }
    }
}