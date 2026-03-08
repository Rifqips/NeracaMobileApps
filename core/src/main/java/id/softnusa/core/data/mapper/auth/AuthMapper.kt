package id.softnusa.core.data.mapper.auth

import id.softnusa.core.data.remote.model.request.auth.RequestLoginDto
import id.softnusa.core.data.remote.model.request.auth.RequestLogoutDto
import id.softnusa.core.data.remote.model.request.auth.RequestTokentDto
import id.softnusa.core.data.remote.model.response.auth.ResponseLoginDto
import id.softnusa.core.domain.model.request.auth.RequestLogin
import id.softnusa.core.domain.model.request.auth.RequestLogout
import id.softnusa.core.domain.model.request.auth.RequestToken
import id.softnusa.core.domain.model.response.auth.ResponseLogin


fun RequestLogin.toDto(): RequestLoginDto {
    return RequestLoginDto(
        username = username,
        password = password
    )
}

fun ResponseLoginDto.toDomain(): ResponseLogin {
    return ResponseLogin(
        accessToken = accessToken,
        refreshToken = refreshToken
    )
}

fun RequestToken.toDto(): RequestTokentDto{
    return RequestTokentDto(
        refreshToken = refreshToken
    )
}

fun RequestLogout.toDto() : RequestLogoutDto{
    return RequestLogoutDto(
        refreshToken = refreshToken
    )
}