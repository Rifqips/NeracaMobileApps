package id.softnusa.core.data.remote.api

import id.softnusa.core.data.remote.model.BaseResponseDto
import id.softnusa.core.data.remote.model.request.auth.RequestLoginDto
import id.softnusa.core.data.remote.model.request.auth.RequestLogoutDto
import id.softnusa.core.data.remote.model.response.auth.ResponseLoginDto

interface AuthApi {

    suspend fun login(
        request: RequestLoginDto
    ): BaseResponseDto<ResponseLoginDto>

    suspend fun register(
        request: RequestLoginDto
    ): BaseResponseDto<ResponseLoginDto>

    suspend fun refreshToken(
        refreshToken: String
    ): BaseResponseDto<ResponseLoginDto>

    suspend fun logout(
        request: RequestLogoutDto
    ): BaseResponseDto<ResponseLoginDto>
}