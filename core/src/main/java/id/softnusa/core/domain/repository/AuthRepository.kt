package id.softnusa.core.domain.repository

import id.softnusa.core.domain.model.request.auth.RequestLogin
import id.softnusa.core.domain.model.response.auth.ResponseLogin
import id.softnusa.core.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    fun login(
        request: RequestLogin
    ): Flow<Resource<ResponseLogin>>

    fun register(
        request: RequestLogin
    ): Flow<Resource<ResponseLogin>>

    fun logout(): Flow<Resource<ResponseLogin?>>

    fun getUsername() : Flow<String?>
}