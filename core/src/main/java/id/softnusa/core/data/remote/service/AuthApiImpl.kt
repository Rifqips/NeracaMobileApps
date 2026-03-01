package id.softnusa.core.data.remote.service

import id.softnusa.core.data.remote.api.AuthApi
import id.softnusa.core.data.remote.model.BaseResponseDto
import id.softnusa.core.data.remote.model.request.prelogin.RequestLoginDto
import id.softnusa.core.data.remote.model.response.prelogin.ResponseLoginDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import javax.inject.Inject

class AuthApiImpl @Inject constructor(
    private val client: HttpClient
) : AuthApi {

    override suspend fun login(
        request: RequestLoginDto
    ): BaseResponseDto<ResponseLoginDto> {

        return client.post("auth/login") {
            setBody(request)
        }.body()
    }

    override suspend fun register(request: RequestLoginDto): BaseResponseDto<ResponseLoginDto> {
        return client.post("auth/register") {
            setBody(request)
        }.body()
    }

    override suspend fun refreshToken(
        refreshToken: String
    ): BaseResponseDto<ResponseLoginDto> {

        return client.post("auth/refresh") {
            setBody(mapOf("refreshToken" to refreshToken))
        }.body()
    }
}