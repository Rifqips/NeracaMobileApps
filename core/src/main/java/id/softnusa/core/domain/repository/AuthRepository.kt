package id.softnusa.core.domain.repository

import id.softnusa.core.data.remote.model.BaseResponseDto
import id.softnusa.core.data.remote.model.response.auth.ResponseLoginDto
import id.softnusa.core.domain.model.BaseResponse
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


    fun refreshToken(
        refreshToken: String
    ): BaseResponse<ResponseLogin>

    fun logout(): Flow<Resource<ResponseLogin?>>

    fun getUsername() : Flow<String?>
}